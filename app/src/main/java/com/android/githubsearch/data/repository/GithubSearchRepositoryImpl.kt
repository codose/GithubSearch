package com.android.githubsearch.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.android.githubsearch.data.api.GithubSearchApi
import com.android.githubsearch.data.paging.SearchPagingSource
import com.android.githubsearch.domain.mapper.SearchMapper
import com.android.githubsearch.domain.model.SearchDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GithubSearchRepositoryImpl @Inject constructor(
    private val searchMapper: SearchMapper,
    private val searchApi: GithubSearchApi,
    private val pagingConfig: PagingConfig
) : GithubSearchRepository {
    override suspend fun searchUser(query: String): Flow<PagingData<SearchDomain>> = Pager(pagingConfig) {
        SearchPagingSource(searchApi, query)
    }.flow.map {
        it.map { schema ->
            searchMapper.mapToDomain(schema)
        }
    }
}
