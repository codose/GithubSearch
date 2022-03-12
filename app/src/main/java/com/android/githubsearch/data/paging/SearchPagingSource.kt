package com.android.githubsearch.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.android.githubsearch.domain.model.SearchDomain
import com.android.githubsearch.domain.usecase.SearchUserUseCase

class SearchPagingSource(
    private val searchUserUseCase: SearchUserUseCase,
    private val query: String
) : PagingSource<Int, SearchDomain>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, SearchDomain> {
        val currentPage = params.key ?: 1
        return try {
            val items = searchUserUseCase.execute(query = query, page = currentPage)
            val endOfPaginationReached = items.isEmpty()
            if (endOfPaginationReached.not()) {
                LoadResult.Page(
                    data = items,
                    prevKey = if (currentPage == 1) null else currentPage - 1,
                    nextKey = if (endOfPaginationReached) null else currentPage + 1
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

    override fun getRefreshKey(state: PagingState<Int, SearchDomain>): Int? {
        return state.anchorPosition
    }
}
