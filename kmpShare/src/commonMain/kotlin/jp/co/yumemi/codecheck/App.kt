package jp.co.yumemi.codecheck

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import jp.co.yumemi.codecheck.ui.search.SearchScreen

@Composable
fun App() {
    MaterialTheme {
        Navigator(SearchScreen())
    }
}
