package com.android.githubsearch.di

import com.android.githubsearch.data.repository.GithubSearchRepository
import com.android.githubsearch.data.repository.GithubSearchRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindGithubRepository(githubSearchRepositoryImpl: GithubSearchRepositoryImpl): GithubSearchRepository
}
