package jp.co.yumemi.android.codecheck.ui.search

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import jp.co.yumemi.android.codecheck.ui.detail.RepositoryDetailScreen

class SearchScreen : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val screenModel = koinScreenModel<SearchScreenModel>()
        val state by screenModel.state.collectAsState()

        SearchContent(
            list = state.list,
            isLoading = state.isLoading,
            onTextChanged = { screenModel.searchResults(it) },
            onNavigate = { navigator.push(RepositoryDetailScreen(it)) },
        )
    }
}
