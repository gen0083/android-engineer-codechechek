package jp.co.yumemi.android.codecheck.ui

import androidx.compose.ui.input.key.Key
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onParent
import androidx.compose.ui.test.performKeyInput
import androidx.compose.ui.test.performTextInput
import com.github.takahirom.roborazzi.captureRoboImage
import jp.co.yumemi.codecheck.App
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.GraphicsMode

@RunWith(RobolectricTestRunner::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
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

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun inputTest() = runTest {
        composeRule.setContent { App() }
        composeRule.awaitIdle()
        composeRule.onNodeWithTag("edit")
            .performTextInput("gen0083")
        composeRule.onNodeWithTag("edit")
            .performKeyInput { keyDown(Key.Search) }
        composeRule.waitForIdle()
        composeRule.onNodeWithTag("edit")
            .onParent()
            .captureRoboImage("build/compose.png")
        composeRule.onNode(hasText("gen0083/textlint-myrule"))
            .assertExists()
    }
}
