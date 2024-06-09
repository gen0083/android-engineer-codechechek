package jp.co.yumemi.codecheck.ui.search

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import jp.co.yumemi.codecheck.ui.components.MyTopAppBar
import jp.co.yumemi.codecheck.ui.detail.RepositoryDetailScreen
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class SearchScreen : Screen, KoinComponent {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val screenModel = rememberScreenModel<SearchScreenModel> {
            inject<SearchScreenModel>().value
        }
        val state by screenModel.state.collectAsState()

        Scaffold(
            topBar = {
                MyTopAppBar(
                    navigator = navigator,
                    isRoot = true,
                )
            },
        ) {
            SearchContent(
                list = state.list,
                isLoading = state.isLoading,
                onTextChanged = { screenModel.searchResults(it) },
                onNavigate = { navigator.push(RepositoryDetailScreen(it)) },
                modifier = Modifier.padding(paddingValues = it),
            )
        }
    }
}
