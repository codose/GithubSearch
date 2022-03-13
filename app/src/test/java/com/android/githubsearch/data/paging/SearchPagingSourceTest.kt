package com.android.githubsearch.data.paging

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.PagingSource
import com.android.githubsearch.CoroutineTestRule
import com.android.githubsearch.data.api.GithubSearchApi
import com.android.githubsearch.data.model.SearchResponseSchema
import com.android.githubsearch.data.model.SearchSchema
import com.android.githubsearch.domain.model.SearchDomain
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class SearchPagingSourceTest {
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var coroutineTestRule = CoroutineTestRule()

    private val mockedSearchSchema = SearchSchema(
        avatarUrl = "",
        eventsUrl = "",
        followersUrl = "",
        followingUrl = "",
        gistsUrl = "",
        gravatarId = "",
        htmlUrl = "",
        id = 0,
        login = "",
        nodeId = "",
        organizationsUrl = "",
        receivedEventsUrl = "",
        reposUrl = "",
        score = 0.0,
        siteAdmin = false,
        starredUrl = "",
        subscriptionsUrl = "",
        type = "",
        url = ""
    )

    private val mockedResponseSchema = SearchResponseSchema(
        incompleteResults = true,
        items = listOf(mockedSearchSchema, mockedSearchSchema, mockedSearchSchema),
        totalCount = 2
    )

    private val mockSearchApi = mockk<GithubSearchApi>()
    lateinit var sut: SearchPagingSource
    private val searchDomain = SearchDomain(
        avatarUrl = "",
        id = 0,
        login = "",
        type = ""
    )

    @Before
    fun setup() {
        sut = SearchPagingSource(mockSearchApi, "")
    }

    @Test
    fun `given when paging source load is called and an error occurs, it should return error`() = runTest {
        val error = RuntimeException("404", Throwable())
        coEvery { mockSearchApi.searchUser(any(), any(), any()) } throws error
        val expectedResult = PagingSource.LoadResult.Error<Int, SearchDomain>(error)
        assertEquals(
            expectedResult,
            sut.load(
                PagingSource.LoadParams.Refresh(
                    key = 0,
                    loadSize = 1,
                    placeholdersEnabled = false
                )
            )
        )
    }

    @Test
    fun `given when paging source load is called and request is successful, it should return correct data`() = runTest {
        coEvery { mockSearchApi.searchUser(any(), any(), any()) } returns mockedResponseSchema
        val expectedResult = PagingSource.LoadResult.Page(
            data = listOf(mockedSearchSchema, mockedSearchSchema, mockedSearchSchema),
            prevKey = 1,
            nextKey = 3
        )
        assertEquals(
            expectedResult,
            sut.load(
                PagingSource.LoadParams.Append(
                    key = 2,
                    loadSize = 1,
                    placeholdersEnabled = false
                )
            )
        )
    }

    @Test
    fun `given when paging source load is called and the list is empty, prev key and next key should be null`() = runTest {
        coEvery { mockSearchApi.searchUser(any(), any(), any()) } returns mockedResponseSchema.copy(items = listOf())
        val expectedResult = PagingSource.LoadResult.Page(
            data = listOf(),
            prevKey = null,
            nextKey = null
        )
        assertEquals(
            expectedResult,
            sut.load(
                PagingSource.LoadParams.Append(
                    key = 2,
                    loadSize = 1,
                    placeholdersEnabled = false
                )
            )
        )
    }
}
