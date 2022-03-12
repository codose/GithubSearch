package com.android.githubsearch.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.githubsearch.domain.mapper.SearchMapper
import com.android.githubsearch.domain.usecase.SearchUserUseCase
import com.android.githubsearch.ui.model.SearchItem
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

    private val _viewState = MutableStateFlow<ViewState>(ViewState.Idle)

    val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        _viewState.value = ViewState.Error(message = exception.message ?: "An error occurred")
        Timber.e("An Error Occurred: $exception")
    }

    val viewState = _viewState.asStateFlow()

    fun searchUser(query: String) {
        _viewState.value = ViewState.Loading
        viewModelScope.launch(coroutineExceptionHandler) {
            val data = searchUserUseCase.execute(query, 1).map {
                searchMapper.mapToPresentation(it)
            }
            _viewState.value = ViewState.Data(data = data)
        }
    }

    sealed class ViewState {
        object Loading : ViewState()
        data class Data(val data: List<SearchItem>) : ViewState()
        data class Error(val message: String) : ViewState()
        object Idle : ViewState()
    }
}
