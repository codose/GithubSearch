package com.android.githubsearch.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.android.githubsearch.data.api.GithubSearchApi
import com.android.githubsearch.data.model.SearchSchema
import com.android.githubsearch.utils.Constants
import com.android.githubsearch.utils.Constants.IN_LOGIN

class SearchPagingSource(
    private val searchApi: GithubSearchApi,
    private val query: String,
) : PagingSource<Int, SearchSchema>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, SearchSchema> {
        val currentPage = params.key ?: 1
        return try {
            val items = searchApi.searchUser(query = "$query$IN_LOGIN", page = currentPage, perPage = Constants.NO_PER_PAGE)
            val endOfPaginationReached = items.items.isEmpty()
            if (endOfPaginationReached.not()) {
                LoadResult.Page(
                    data = items.items,
                    prevKey = if (currentPage == 1) null else currentPage - 1,
                    nextKey = if (endOfPaginationReached || Constants.NO_PER_PAGE >= items.totalCount) null else currentPage + 1
                )
            } else {
                LoadResult.Page(
                    data = emptyList(),
                    prevKey = null,
                    nextKey = null
                )
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, SearchSchema>): Int? {
        return state.anchorPosition
    }
}
