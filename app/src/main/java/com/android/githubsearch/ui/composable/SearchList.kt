package com.android.githubsearch.ui.composable

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items
import com.android.githubsearch.ui.model.SearchItem
import retrofit2.HttpException

@Composable
fun SearchList(searchResult: LazyPagingItems<SearchItem>) {
    LazyColumn {
        items(
            items = searchResult
        ) { result ->
            result?.let { SearchListItem(it) }
        }
        searchResult.apply {
            when {
                loadState.refresh is LoadState.Loading -> {
                    item {
                        FullScreenProgress(Modifier.fillParentMaxSize())
                    }
                }
                loadState.append is LoadState.Loading -> {
                    item {
                        FullScreenProgress(Modifier.fillMaxWidth())
                    }
                }
                loadState.refresh is LoadState.Error -> {
                    val e = searchResult.loadState.refresh as LoadState.Error
                    item {
                        ErrorPage(
                            modifier = Modifier.fillParentMaxSize(),
                            message = getErrorMessage(e.error),
                            onClickRetry = { retry() }
                        )
                    }
                }
                loadState.append is LoadState.Error -> {
                    val e = searchResult.loadState.append as LoadState.Error
                    item {
                        ErrorPage(
                            modifier = Modifier.fillMaxSize(),
                            message = getErrorMessage(e.error),
                            onClickRetry = { retry() }
                        )
                    }
                }
                itemCount == 0 -> {
                    item {
                        IdleState(Modifier.fillParentMaxSize())
                    }
                }
            }
        }
    }
}

private fun getErrorMessage(throwable: Throwable): String =
    if ((throwable as? HttpException)?.code() == 403) "Request Limit Reached" else "An error occurred"
