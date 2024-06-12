package jp.co.yumemi.codecheck.ui.search

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text2.input.rememberTextFieldState
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
import kotlinx.coroutines.FlowPreview
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class SearchScreen : Screen, KoinComponent {
    @FlowPreview
    @ExperimentalFoundationApi
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val screenModel = rememberScreenModel<SearchScreenModel> {
            inject<SearchScreenModel>().value
        }
        val state by screenModel.state.collectAsState()
        val textFieldState = rememberTextFieldState(initialText = "")

        screenModel.connectTextFieldState(textFieldState)

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
                textState = textFieldState,
                onTextChanged = { screenModel.searchResults(it) },
                onNavigate = { navigator.push(RepositoryDetailScreen(it)) },
                modifier = Modifier.padding(paddingValues = it),
            )
        }
    }
}
