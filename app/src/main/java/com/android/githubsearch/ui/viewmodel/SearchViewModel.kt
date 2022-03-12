package com.android.githubsearch.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.android.githubsearch.data.paging.SearchPagingSource
import com.android.githubsearch.domain.mapper.SearchMapper
import com.android.githubsearch.domain.usecase.SearchUserUseCase
import com.android.githubsearch.ui.model.SearchItem
import com.android.githubsearch.utils.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchMapper: SearchMapper,
    private val searchUserUseCase: SearchUserUseCase
) : ViewModel() {

    private val _searchResult = MutableStateFlow<PagingData<SearchItem>>(PagingData.empty())
    val searchResult = _searchResult.asStateFlow()

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        Timber.e("An Error Occurred: $exception")
    }

    fun searchUser(query: String) {
        viewModelScope.launch(coroutineExceptionHandler) {
            Pager(PagingConfig(pageSize = Constants.NO_PER_PAGE)) {
                SearchPagingSource(searchUserUseCase, query)
            }.flow.collect {
                _searchResult.value = it.map { domain ->
                    searchMapper.mapToPresentation(domain)
                }
            }
        }
    }
}
