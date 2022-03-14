package com.android.githubsearch.ui.composable

import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import com.android.githubsearch.ui.theme.GithubSearchTheme
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class IdleStateTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `given when ErrorPage composable is displayed, it should display the correct text`() {
        composeTestRule.setContent {
            GithubSearchTheme {
                IdleState(modifier = Modifier)
            }
        }

        composeTestRule.onNodeWithTag("idleText").apply {
            assertIsDisplayed()
            assertTextContains("Search for github users")
        }
    }
}
