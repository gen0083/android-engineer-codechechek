package jp.co.yumemi.codecheck.ui.detail

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.compose.foundation.background
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onRoot
import coil3.ImageLoader
import coil3.SingletonImageLoader
import coil3.compose.LocalPlatformContext
import coil3.test.FakeImage
import coil3.test.FakeImageLoaderEngine
import coil3.test.default
import com.github.takahirom.roborazzi.captureRoboImage
import jp.co.yumemi.codecheck.api.RepositoryInfo
import jp.co.yumemi.codecheck.api.RepositoryOwner
import jp.co.yumemi.codecheck.ui.theme.AppTheme
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.GraphicsMode
import org.robolectric.shadows.ShadowLog

@RunWith(RobolectricTestRunner::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
class RepositoryDetailContentTest {
    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun default() = runTest {
        ShadowLog.stream = System.out
        composeRule.setContent {
            val engine = FakeImageLoaderEngine.Builder()
                .intercept(
                    "https://avatars.githubusercontent.com/u/7608725?v=4",
                    FakeImage(100, 100, 100, true, Color.RED),
                )
                .default(ColorDrawable(Color.RED))
                .build()
            val imageLoader = ImageLoader.Builder(LocalPlatformContext.current)
                .components { add(engine) }
                .build()
            SingletonImageLoader.setUnsafe(imageLoader)
            AppTheme {
                RepositoryDetailContent(
                    info = RepositoryInfo(
                        name = "some",
                        fullName = "default/some",
                        owner = RepositoryOwner(
                            ownerName = "test",
                            ownerIconUrl = "https://avatars.githubusercontent.com/u/7608725?v=4",
                        ),
                        language = "kotlin",
                        description = "kotlin sample",
                        stargazersCount = 10,
                        watchersCount = 1,
                        forksCount = 100,
                        openIssuesCount = 1111,
                    ),
                    lastSearchDate = "検索日: 2024年1月1日 01:02:03",
                    onShowBottomSheet = {},
                    modifier = Modifier.background(androidx.compose.ui.graphics.Color.White),
                )
            }
        }
        composeRule.onNodeWithText(text = "some", substring = true)
            .assertExists()
        composeRule.onRoot().captureRoboImage()
    }
}
