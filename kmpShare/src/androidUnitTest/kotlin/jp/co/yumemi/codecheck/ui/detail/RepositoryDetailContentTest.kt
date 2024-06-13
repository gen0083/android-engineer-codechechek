package jp.co.yumemi.codecheck.ui.detail

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onRoot
import com.github.takahirom.roborazzi.captureRoboImage
import jp.co.yumemi.codecheck.api.RepositoryInfo
import jp.co.yumemi.codecheck.api.RepositoryOwner
import jp.co.yumemi.codecheck.ui.theme.AppTheme
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.GraphicsMode

@RunWith(RobolectricTestRunner::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
class RepositoryDetailContentTest {
    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun default() {
        composeRule.setContent {
            AppTheme {
                RepositoryDetailContent(
                    info = RepositoryInfo(
                        name = "default/some",
                        owner = RepositoryOwner(
                            ownerIconUrl = "",
                        ),
                        language = "kotlin",
                        stargazersCount = 10,
                        watchersCount = 1,
                        forksCount = 100,
                        openIssuesCount = 1111,
                    ),
                    lastSearchDate = "検索日: 2024年1月1日 01:02:03",
                )
            }
        }
        composeRule.onNodeWithText("default")
            .assertExists()
        composeRule.onRoot().captureRoboImage()
    }
}
