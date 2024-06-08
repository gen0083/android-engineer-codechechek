package jp.co.yumemi.codecheck.ui.search

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import jp.co.yumemi.codecheck.ui.detail.RepositoryDetailScreen
import org.koin.core.component.KoinComponent

class SearchScreen : Screen, KoinComponent {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val screenModel = rememberScreenModel<SearchScreenModel> {
            getKoin().get<SearchScreenModel>()
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
