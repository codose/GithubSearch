package com.android.githubsearch.data.repository

import com.android.githubsearch.domain.model.SearchDomain

interface GithubSearchRepository {
    suspend fun searchUser(query: String, page: Int): List<SearchDomain>
}
