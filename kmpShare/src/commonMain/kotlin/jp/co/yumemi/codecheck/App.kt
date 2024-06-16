package jp.co.yumemi.codecheck

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.bottomSheet.BottomSheetNavigator
import jp.co.yumemi.codecheck.di.networkModule
import jp.co.yumemi.codecheck.di.screenModelModule
import jp.co.yumemi.codecheck.ui.search.SearchScreen
import jp.co.yumemi.codecheck.ui.theme.AppTheme
import org.koin.core.context.startKoin

val koin = startKoin {
    modules(
        networkModule,
        screenModelModule,
    )
}

@Composable
fun App() {
    AppTheme {
        BottomSheetNavigator(
            hideOnBackPress = true,
            sheetShape = MaterialTheme.shapes.extraLarge,
        ) {
            Navigator(SearchScreen())
        }
    }
}
