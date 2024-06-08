package jp.co.yumemi.codecheck.ui.search

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import jp.co.yumemi.codecheck.api.SearchClient
import jp.co.yumemi.codecheck.httpClient
import jp.co.yumemi.codecheck.ui.detail.RepositoryDetailScreen
import kotlinx.serialization.json.Json

class SearchScreen : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val screenModel = rememberScreenModel<SearchScreenModel> {
            // TODO: 一時的にこうしてるだけ
            SearchScreenModel(
                SearchClient(
                    httpClient(),
                    Json { ignoreUnknownKeys = true },
                ),
            )
        }
        val state by screenModel.state.collectAsState()

        SearchContent(
            list = state.list,
            isLoading = state.isLoading,
            onTextChanged = { screenModel.searchResults(it) },
            onNavigate = { navigator.push(RepositoryDetailScreen(it)) },
        )
    }
}
