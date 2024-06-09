package jp.co.yumemi.codecheck.ui.detail

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import jp.co.yumemi.codecheck.api.RepositoryInfo
import jp.co.yumemi.codecheck.resources.Res
import jp.co.yumemi.codecheck.resources.app_name
import org.jetbrains.compose.resources.stringResource
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

data class RepositoryDetailScreen(
    private val info: RepositoryInfo,
) : Screen, KoinComponent {
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {
        val screenModel: RepositoryDetailScreenModel =
            rememberScreenModel<RepositoryDetailScreenModel> {
                inject<RepositoryDetailScreenModel>().value
            }
        val navigator = LocalNavigator.currentOrThrow

        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(text = stringResource(Res.string.app_name))
                    },
                    navigationIcon = {
                        IconButton(
                            onClick = { navigator.pop() },
                            content = {
                                Icon(
                                    imageVector = Icons.AutoMirrored.Default.ArrowBack,
                                    contentDescription = "back",
                                )
                            },
                        )
                    },
                    colors = TopAppBarDefaults.topAppBarColors(),
                )
            },
        ) {
            RepositoryDetailContent(
                info = info,
                lastSearchDate = screenModel.getLastSearchTime(),
                modifier = Modifier.padding(it),
            )
        }
    }
}
