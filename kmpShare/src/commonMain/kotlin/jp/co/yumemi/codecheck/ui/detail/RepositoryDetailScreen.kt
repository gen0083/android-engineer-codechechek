package jp.co.yumemi.codecheck.ui.detail

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import jp.co.yumemi.codecheck.api.RepositoryInfo
import jp.co.yumemi.codecheck.api.SearchClient
import jp.co.yumemi.codecheck.httpClient
import kotlinx.serialization.json.Json

data class RepositoryDetailScreen(
    private val info: RepositoryInfo,
) : Screen {
    @Composable
    override fun Content() {
        val screenModel: RepositoryDetailScreenModel =
            rememberScreenModel<RepositoryDetailScreenModel> {
                // TODO: 一時的にこうしてるだけ
                RepositoryDetailScreenModel(
                    SearchClient(
                        client = httpClient(),
                        json = Json {
                            ignoreUnknownKeys = true
                        },
                    ),
                )
            }

        RepositoryDetailContent(
            info = info,
            lastSearchDate = screenModel.getLastSearchTime(),
        )
    }
}
