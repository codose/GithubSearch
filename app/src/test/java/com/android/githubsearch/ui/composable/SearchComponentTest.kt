package com.android.githubsearch.ui.composable

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performImeAction
import androidx.compose.ui.test.performTextInput
import com.android.githubsearch.ui.theme.GithubSearchTheme
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class SearchComponentTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `given when SearchComponent composable is displayed, data is entered and the search is clicked, assert that the query is returned`() {
        var actualText = ""
        val expectedQuery = "Hello there"
        composeTestRule.setContent {
            GithubSearchTheme {
                SearchComponent {
                    actualText = it
                }
            }
        }

        val textField = composeTestRule.onNodeWithTag("searchTextField")
        val searchButton = composeTestRule.onNodeWithTag("searchButton")
        textField.performClick()
        textField.performTextInput(expectedQuery)

        searchButton.performClick()

        assertEquals(expectedQuery, actualText)
    }

    @Test
    fun `given when SearchComponent composable is displayed, data is entered and the user performs search from the keyboard, assert that the query is returned`() {
        var actualText = ""
        val expectedQuery = "Hello there"
        composeTestRule.setContent {
            GithubSearchTheme {
                SearchComponent {
                    actualText = it
                }
            }
        }

        val textField = composeTestRule.onNodeWithTag("searchTextField")
        textField.performClick()
        textField.performTextInput(expectedQuery)
        textField.performImeAction()
        assertEquals(expectedQuery, actualText)
    }

    @Test
    fun `given when SearchComponent composable is displayed, blank spaces are entered and the user performs search from the keyboard, should not invoke the function`() {
        var actualText: String? = null
        val query = "           "
        composeTestRule.setContent {
            GithubSearchTheme {
                SearchComponent {
                    actualText = it
                }
            }
        }

        val textField = composeTestRule.onNodeWithTag("searchTextField")
        textField.performClick()
        textField.performTextInput(query)
        textField.performImeAction()
        assertEquals(null, actualText)
    }

    @Test
    fun `given when SearchComponent composable is displayed, blank spaces are entered and the search is clicked, should not invoke the function`() {
        var actualText: String? = null
        val query = "                       "
        composeTestRule.setContent {
            GithubSearchTheme {
                SearchComponent {
                    actualText = it
                }
            }
        }

        val textField = composeTestRule.onNodeWithTag("searchTextField")
        val searchButton = composeTestRule.onNodeWithTag("searchButton")
        textField.performClick()
        textField.performTextInput(query)

        searchButton.performClick()

        assertEquals(null, actualText)
    }
}
