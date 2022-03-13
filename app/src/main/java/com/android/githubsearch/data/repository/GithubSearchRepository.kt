package com.android.githubsearch.data.repository

import androidx.paging.PagingData
import com.android.githubsearch.domain.model.SearchDomain
import kotlinx.coroutines.flow.Flow

interface GithubSearchRepository {
    suspend fun searchUser(query: String): Flow<PagingData<SearchDomain>>
}
