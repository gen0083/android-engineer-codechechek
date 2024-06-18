package jp.co.yumemi.codecheck.ui.detail

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.bottomSheet.LocalBottomSheetNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import jp.co.yumemi.codecheck.api.RepositoryInfo
import jp.co.yumemi.codecheck.ui.components.MyTopAppBar
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

data class RepositoryDetailScreen(
    private val info: RepositoryInfo,
) : Screen, KoinComponent {
    @Composable
    override fun Content() {
        val screenModel: RepositoryDetailScreenModel =
            rememberScreenModel<RepositoryDetailScreenModel>(tag = info.owner.ownerName) {
                inject<RepositoryDetailScreenModel>().value
            }
        val navigator = LocalNavigator.currentOrThrow
        val bottomNavigator = LocalBottomSheetNavigator.current

        Scaffold(
            topBar = {
                MyTopAppBar(
                    navigator = navigator,
                    isRoot = false,
                )
            },
        ) {
            RepositoryDetailContent(
                info = info,
                lastSearchDate = screenModel.getLastSearchTime(),
                onNavigate = {
                    bottomNavigator.show(
                        RepositoryListScreen(
                            info.owner.ownerName,
                        ) {
                            navigator.replace(RepositoryDetailScreen(it))
                        },
                    )
                },
                modifier = Modifier.padding(it),
                debug = navigator.items,
            )
        }
    }
}
