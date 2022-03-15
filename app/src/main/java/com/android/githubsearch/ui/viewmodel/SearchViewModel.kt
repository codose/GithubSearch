package com.android.githubsearch.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.android.githubsearch.domain.mapper.SearchMapper
import com.android.githubsearch.domain.usecase.SearchUserUseCase
import com.android.githubsearch.ui.model.SearchItem
import com.android.githubsearch.utils.DispatcherProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchMapper: SearchMapper,
    private val searchUserUseCase: SearchUserUseCase,
    private val dispatchers: DispatcherProvider
) : ViewModel() {

    private val _searchResult = MutableStateFlow<PagingData<SearchItem>>(PagingData.empty())
    val searchResult = _searchResult.asStateFlow()

    fun searchUser(query: String) {
        _searchResult.value = PagingData.empty()
        viewModelScope.launch(dispatchers.main) {
            // Delay is necessary to reset the pagedlist before sending another request
            delay(LIST_RESET_DELAY)
            searchUserUseCase.execute(query)
                .cachedIn(viewModelScope)
                .collect {
                    _searchResult.value = it.map { domain ->
                        searchMapper.mapToPresentation(domain)
                    }
                }
        }
    }

    companion object {
        private const val LIST_RESET_DELAY = 100L
    }
}
