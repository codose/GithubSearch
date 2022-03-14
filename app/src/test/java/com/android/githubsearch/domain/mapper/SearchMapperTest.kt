package com.android.githubsearch.domain.mapper

import com.android.githubsearch.data.model.SearchSchema
import com.android.githubsearch.domain.model.SearchDomain
import com.android.githubsearch.ui.model.SearchItem
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.*
import org.junit.Test

class SearchMapperTest {
    private val sut = SearchMapper()

    private val mockedAvatarUrl = "mockUrl"
    private val mockedId = 98
    private val mockedLogin = "login"
    private val mockedType = "type"

    private val mockedSearchSchema = mockk<SearchSchema>().apply {
        every { avatarUrl } returns mockedAvatarUrl
        every { id } returns mockedId
        every { login } returns mockedLogin
        every { type } returns mockedType
    }

    private val mockedSearchDomain = SearchDomain(
        avatarUrl = mockedAvatarUrl,
        id = mockedId,
        login = mockedLogin,
        type = mockedType
    )

    private val mockedSearchItem = SearchItem(
        avatarUrl = mockedAvatarUrl,
        id = mockedId,
        login = mockedLogin,
        type = mockedType
    )

    @Test
    fun `test schema mapping`() {
        val actualResult = sut.mapToDomain(mockedSearchSchema)
        assertEquals(mockedSearchDomain, actualResult)
    }

    @Test
    fun `test domain mapping`() {
        val actualResult = sut.mapToPresentation(mockedSearchDomain)
        assertEquals(mockedSearchItem.avatarUrl, actualResult.avatarUrl)
        assertEquals(mockedSearchItem, actualResult)
    }
}
