package com.android.githubsearch.data.model

import com.google.gson.annotations.SerializedName

data class SearchResponseSchema(
    @SerializedName("incomplete_results")
    val incompleteResults: Boolean,
    @SerializedName("items")
    val items: List<SearchSchema>,
    @SerializedName("total_count")
    val totalCount: Int
)
