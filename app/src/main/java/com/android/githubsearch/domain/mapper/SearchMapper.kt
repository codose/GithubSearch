package com.android.githubsearch.domain.mapper

import com.android.githubsearch.data.model.SearchSchema
import com.android.githubsearch.domain.model.SearchDomain
import com.android.githubsearch.ui.model.SearchItem
import javax.inject.Inject

class SearchMapper @Inject constructor() {
    fun mapToDomain(schema: SearchSchema): SearchDomain = SearchDomain(
        avatarUrl = schema.avatarUrl,
        id = schema.id,
        login = schema.login,
        type = schema.type
    )

    fun mapToPresentation(domain: SearchDomain): SearchItem = SearchItem(
        avatarUrl = domain.avatarUrl,
        id = domain.id,
        login = domain.login,
        type = domain.type
    )
}
