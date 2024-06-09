package jp.co.yumemi.codecheck.ui.search

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import jp.co.yumemi.codecheck.resources.Res
import jp.co.yumemi.codecheck.resources.app_name
import jp.co.yumemi.codecheck.ui.detail.RepositoryDetailScreen
import org.jetbrains.compose.resources.stringResource
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class SearchScreen : Screen, KoinComponent {
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val screenModel = rememberScreenModel<SearchScreenModel> {
            inject<SearchScreenModel>().value
        }
        val state by screenModel.state.collectAsState()

        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(text = stringResource(Res.string.app_name))
                    },
                    navigationIcon = {
                        if (navigator.parent != null) {
                            IconButton(
                                onClick = { navigator.pop() },
                                content = {
                                    Icon(
                                        imageVector = Icons.AutoMirrored.Default.ArrowBack,
                                        contentDescription = "back",
                                    )
                                },
                            )
                        }
                    },
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
