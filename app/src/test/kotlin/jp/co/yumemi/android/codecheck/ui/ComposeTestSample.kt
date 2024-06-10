package jp.co.yumemi.android.codecheck.ui

import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performTextInput
import jp.co.yumemi.codecheck.App
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class ComposeTestSample {
    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun test() {
        composeRule.setContent {
            App()
        }

        composeRule
            .onNode(hasText("empty"))
            .assertExists()
    }

    @Test
    fun inputTest() = runTest {
        composeRule.setContent { App() }
        composeRule.awaitIdle()
        composeRule.onNodeWithTag("edit")
            .performTextInput("gen0083")
        composeRule.waitForIdle()
        composeRule.onNode(hasText("gen0083/textlint-myrule"))
            .assertExists()
    }
}
