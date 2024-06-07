package jp.co.yumemi.android.codecheck.ui

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen

class SearchScreen : Screen {
    @Composable
    override fun Content() {
        SearchContent(
            // TODO: replace real data
            list = listOf(),
            isLoading = false,
            onTextChanged = {},
            onNavigate = {},
        )
    }
}
