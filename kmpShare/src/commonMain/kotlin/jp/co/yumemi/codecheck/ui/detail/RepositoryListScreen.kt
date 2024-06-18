package jp.co.yumemi.codecheck.ui.detail

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.model.rememberNavigatorScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.bottomSheet.LocalBottomSheetNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import jp.co.yumemi.codecheck.api.RepositoryInfo
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.parameter.parametersOf

data class RepositoryListScreen(
    val userName: String,
    val onNavigate: (RepositoryInfo) -> Unit,
) : Screen, KoinComponent {
    @ExperimentalMaterial3Api
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val bottomNavigator = LocalBottomSheetNavigator.current
        val screenModel = navigator.rememberNavigatorScreenModel(tag = userName) {
            inject<RepositoryListScreenModel> { parametersOf(userName) }.value
        }
        val state by screenModel.state.collectAsState()

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 18.dp)
                .statusBarsPadding()
                .navigationBarsPadding(),
        ) {
            IconButton(
                onClick = { bottomNavigator.hide() },
                modifier = Modifier.align(Alignment.End),
            ) {
                Icon(imageVector = Icons.Default.Close, contentDescription = "close")
            }
            // smart castがState.Successの中で効かないのであえて一旦currentに代入して比較している
            // -> whenでのマッチしたあとでstateが変わっている可能性があるため
            val current = state
            when (current) {
                RepositoryListScreenModel.State.Loading -> {
                    CircularProgressIndicator()
                }

                is RepositoryListScreenModel.State.Success -> {
                    LazyColumn {
                        item {
                            Text(text = "$userName with ${screenModel.hashCode()}")
                        }
                        items(
                            current.repositories.size,
                            key = { current.repositories[it].hashCode() },
                        ) {
                            val item = current.repositories[it]
                            Text(
                                text = item.name,
                                modifier = Modifier.fillMaxWidth()
                                    .clickable {
                                        onNavigate(item)
                                        bottomNavigator.hide()
                                    },
                            )
                        }
                    }
                }

                is RepositoryListScreenModel.State.Error -> {
                    Text(text = "$userName with ${screenModel.hashCode()}")
                    Text(text = "Error: ${current.throwable}")
                    Button(onClick = { screenModel.load() }) {
                        Text(text = "retry")
                    }
                }
            }
        }
    }
}
