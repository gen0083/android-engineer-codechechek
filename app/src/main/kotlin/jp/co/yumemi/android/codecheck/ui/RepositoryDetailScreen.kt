package jp.co.yumemi.android.codecheck.ui

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinScreenModel
import jp.co.yumemi.android.codecheck.api.RepositoryInfo

data class RepositoryDetailScreen(
    private val info: RepositoryInfo,
) : Screen {
    @Composable
    override fun Content() {
        val screenModel: RepositoryDetailScreenModel =
            koinScreenModel<RepositoryDetailScreenModel>()

        RepositoryDetailContent(
            info = info,
            lastSearchDate = screenModel.getLastSearchTime(),
        )
    }
}
