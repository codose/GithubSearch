package com.android.githubsearch.data.api

import com.android.githubsearch.data.model.SearchResponseSchema
import retrofit2.http.GET
import retrofit2.http.Query

interface GithubSearchApi {

    @GET("search/users")
    suspend fun searchUser(
        @Query("q") query: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): SearchResponseSchema
}
