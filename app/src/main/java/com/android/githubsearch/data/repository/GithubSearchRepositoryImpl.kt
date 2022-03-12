package com.android.githubsearch.data.repository

import com.android.githubsearch.data.api.GithubSearchApi
import com.android.githubsearch.domain.mapper.SearchMapper
import com.android.githubsearch.domain.model.SearchDomain
import com.android.githubsearch.utils.Constants.IN_LOGIN
import com.android.githubsearch.utils.Constants.NO_PER_PAGE
import javax.inject.Inject

class GithubSearchRepositoryImpl @Inject constructor(
    private val searchMapper: SearchMapper,
    private val searchApi: GithubSearchApi
) : GithubSearchRepository {
    override suspend fun searchUser(query: String, page: Int): List<SearchDomain> =
        searchApi.searchUser("$query$IN_LOGIN", page, NO_PER_PAGE).items.map {
            searchMapper.mapToDomain(it)
        }
}
