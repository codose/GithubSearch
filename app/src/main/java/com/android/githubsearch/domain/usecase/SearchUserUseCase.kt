package com.android.githubsearch.domain.usecase

import com.android.githubsearch.data.repository.GithubSearchRepository
import com.android.githubsearch.domain.model.SearchDomain
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class SearchUserUseCase @Inject constructor(private val searchRepository: GithubSearchRepository) {
    suspend fun execute(query: String, page: Int): List<SearchDomain> = searchRepository.searchUser(query, page)
}
