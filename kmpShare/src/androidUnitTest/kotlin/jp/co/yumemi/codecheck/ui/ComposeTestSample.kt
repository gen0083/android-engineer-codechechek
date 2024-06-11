package jp.co.yumemi.codecheck.ui

import android.util.Log
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.test.printToLog
import com.github.takahirom.roborazzi.captureRoboImage
import jp.co.yumemi.codecheck.App
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.GraphicsMode
import org.robolectric.shadows.ShadowLog

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
        // printlnは普通にログに出る
        println("test start")
        // Log.dはShadowLog.stream = System.outに切り替えしないと何も出ない
        ShadowLog.stream = System.out
        Log.d("a", "Log.d print message")
        composeRule.setContent { App() }
        composeRule.awaitIdle()
        // テキスト入力しただけでrecompositionは発生しない
        composeRule.onNodeWithTag("edit")
            .performTextInput("gen0083")
        // composeRuleでなにかするとrecompositionが起こる
        // https://developer.android.com/develop/ui/compose/testing/synchronization
        composeRule.waitUntilAtLeastOneExists(
            hasTestTag("result"),
            5_000,
        )
        // Roborazziが画像で出してくれるけども、テキストで確認したい場合はprintToLogが使える
        // ただし中身はLog.dでログに出力しているのでRobolectricを使う場合はログの切り替えをしないと何も出力されない
        // 引数に渡すタグはLog.dで使われるタグ
        composeRule.onRoot().printToLog("test")
        // キャプチャを取る→すでに画像が存在している場合、画像に変化がある場合にテストに失敗する
        composeRule.onRoot()
            .captureRoboImage("build/compose.png")
        composeRule.onNode(hasText("gen0083/textlint-myrule"))
            .assertExists()
    }
}
