package com.android.githubsearch.ui.composable

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items
import coil.annotation.ExperimentalCoilApi
import com.android.githubsearch.ui.model.SearchItem
import retrofit2.HttpException

@ExperimentalCoilApi
@Composable
fun SearchList(searchResult: LazyPagingItems<SearchItem>) {
    LazyColumn {
        item {
            Text("Search Result")
            Spacer(modifier = Modifier.height(16.dp))
        }
        items(
            items = searchResult
        ) { result ->
            result?.let {
                SearchListItem(it)
                Spacer(Modifier.height(16.dp))
            }
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
