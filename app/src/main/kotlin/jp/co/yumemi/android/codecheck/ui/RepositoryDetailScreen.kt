package jp.co.yumemi.android.codecheck.ui

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import jp.co.yumemi.android.codecheck.api.RepositoryInfo

data class RepositoryDetailScreen(private val info: RepositoryInfo) : Screen {
    @Composable
    override fun Content() {
        RepositoryDetailContent(
            // TODO: replace real data
            info = info,
            lastSearchDate = "",
        )
    }
}
