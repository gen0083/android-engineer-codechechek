package jp.co.yumemi.codecheck.ui.detail

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import jp.co.yumemi.codecheck.api.RepositoryInfo
import org.koin.core.component.KoinComponent

data class RepositoryDetailScreen(
    private val info: RepositoryInfo,
) : Screen, KoinComponent {
    @Composable
    override fun Content() {
        val screenModel: RepositoryDetailScreenModel =
            rememberScreenModel<RepositoryDetailScreenModel> {
                getKoin().get<RepositoryDetailScreenModel>()
            }

        RepositoryDetailContent(
            info = info,
            lastSearchDate = screenModel.getLastSearchTime(),
        )
    }
}
