package com.android.githubsearch.ui.composable

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import com.android.githubsearch.ui.model.SearchItem
import kotlinx.coroutines.flow.flowOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class SearchListTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    private val mockedLogin = "login"
    private val mockedLogin2 = "login2"

    private val mockedSearchItem = SearchItem(avatarUrl = "", id = 0, login = mockedLogin, type = "Any")

    private val mockedSearchItem2 = SearchItem(avatarUrl = "", id = 0, login = mockedLogin2, type = "Any")

    @Test
    fun `given SearchList Composable when PagingItems is available, then the correct data should be displayed`() {
        val searchItems = flowOf(PagingData.from(listOf(mockedSearchItem, mockedSearchItem2)))

        composeTestRule.setContent {
            searchItems.collectAsLazyPagingItems()

            SearchList(searchResult = searchItems.collectAsLazyPagingItems())
        }
        composeTestRule.onNodeWithText(mockedLogin).assertIsDisplayed()
        composeTestRule.onNodeWithText(mockedLogin2).assertIsDisplayed()
        composeTestRule.onNodeWithTag("idleText").assertDoesNotExist()
    }

    @Test
    fun `given SearchList Composable when PagingItems is not available, then the correct data should be displayed`() {
        val searchItems = flowOf(PagingData.from(listOf<SearchItem>()))

        composeTestRule.setContent {
            searchItems.collectAsLazyPagingItems()

            SearchList(searchResult = searchItems.collectAsLazyPagingItems())
        }
        composeTestRule.onNodeWithTag("idleText").assertIsDisplayed()
    }
}
