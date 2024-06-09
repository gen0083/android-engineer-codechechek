package jp.co.yumemi.codecheck.ui.detail

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import jp.co.yumemi.codecheck.api.RepositoryInfo
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

data class RepositoryDetailScreen(
    private val info: RepositoryInfo,
) : Screen, KoinComponent {
    @Composable
    override fun Content() {
        val screenModel: RepositoryDetailScreenModel =
            rememberScreenModel<RepositoryDetailScreenModel> {
                inject<RepositoryDetailScreenModel>().value
            }

        RepositoryDetailContent(
            info = info,
            lastSearchDate = screenModel.getLastSearchTime(),
        )
    }
}
