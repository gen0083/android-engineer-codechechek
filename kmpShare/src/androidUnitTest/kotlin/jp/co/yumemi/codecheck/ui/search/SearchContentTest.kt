package jp.co.yumemi.codecheck.ui.search

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.text2.input.rememberTextFieldState
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performTextInput
import com.github.takahirom.roborazzi.captureRoboImage
import jp.co.yumemi.codecheck.api.RepositoryInfo
import jp.co.yumemi.codecheck.api.RepositoryOwner
import jp.co.yumemi.codecheck.ui.theme.AppTheme
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.GraphicsMode

@ExperimentalFoundationApi
@RunWith(RobolectricTestRunner::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
class SearchContentTest {
    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun defaultState_has_empty_text() {
        composeRule.setContent {
            AppTheme {
                SearchContent(
                    list = listOf(),
                    textState = rememberTextFieldState(""),
                    isLoading = false,
                    onTriggerSearch = {},
                    onNavigate = {},
                )
            }
        }
        composeRule.onNodeWithText(text = "empty")
            .assertExists()
        composeRule.onNodeWithContentDescription("clear")
            .assertDoesNotExist()
        composeRule.onRoot().captureRoboImage()
    }

    @Test
    fun inputText_then_clear_button_exist() {
        composeRule.setContent {
            AppTheme {
                SearchContent(
                    list = listOf(
                        RepositoryInfo(
                            name = "aaa",
                            owner = RepositoryOwner(""),
                            language = null,
                            stargazersCount = 1,
                            watchersCount = 2,
                            forksCount = 3,
                            openIssuesCount = 4,
                        ),
                        RepositoryInfo(
                            name = "bbb",
                            owner = RepositoryOwner(""),
                            language = null,
                            stargazersCount = 1,
                            watchersCount = 2,
                            forksCount = 3,
                            openIssuesCount = 4,
                        ),
                    ),
                    textState = rememberTextFieldState(""),
                    isLoading = false,
                    onTriggerSearch = {},
                    onNavigate = {},
                )
            }
        }
        composeRule.onNodeWithContentDescription("clear")
            .assertDoesNotExist()
        composeRule.onNodeWithTag("edit")
            .performTextInput("abc")
        composeRule.onNodeWithContentDescription("clear")
            .assertExists()
        composeRule.onRoot().captureRoboImage()
    }
}
