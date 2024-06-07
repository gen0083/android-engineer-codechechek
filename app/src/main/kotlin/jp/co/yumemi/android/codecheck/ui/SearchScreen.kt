package jp.co.yumemi.android.codecheck.ui

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow

class SearchScreen : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow

        SearchContent(
            // TODO: replace real data
            list = listOf(),
            isLoading = false,
            onTextChanged = {},
            onNavigate = { navigator.push(RepositoryDetailScreen(it)) },
        )
    }
}
