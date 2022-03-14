package com.android.githubsearch.ui.viewmodel

import com.android.githubsearch.CoroutineTestRule
import com.android.githubsearch.domain.mapper.SearchMapper
import com.android.githubsearch.domain.usecase.SearchUserUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class SearchViewModelTest {
    @ExperimentalCoroutinesApi
    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    private lateinit var sut: SearchViewModel
    private val searchMapper = SearchMapper()
    private val searchUserUseCase = mockk<SearchUserUseCase>()

    @Before
    fun setUp() {
        sut = SearchViewModel(searchMapper, searchUserUseCase)
    }

    @Test
    fun `given query when searchUser is executed, verify that searchUserUseCase is executed`() = runTest {
        coEvery {
            searchUserUseCase.execute(any())
        } returns flowOf()

        sut.searchUser("query")

        advanceTimeBy(300L)

        coVerify {
            searchUserUseCase.execute("query")
        }
    }
}
