package com.android.githubsearch.domain.usecase

import androidx.paging.PagingData
import com.android.githubsearch.data.repository.GithubSearchRepository
import com.android.githubsearch.domain.model.SearchDomain
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ViewModelScoped
class SearchUserUseCase @Inject constructor(private val searchRepository: GithubSearchRepository) {
    suspend fun execute(query: String): Flow<PagingData<SearchDomain>> = searchRepository.searchUser(query)
}
