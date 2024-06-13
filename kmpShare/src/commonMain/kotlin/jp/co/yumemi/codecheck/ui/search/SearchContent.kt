package jp.co.yumemi.codecheck.ui.search

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text2.input.TextFieldState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import jp.co.yumemi.codecheck.api.RepositoryInfo
import jp.co.yumemi.codecheck.ui.components.SearchTextField

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SearchContent(
    list: List<RepositoryInfo>,
    isLoading: Boolean,
    textState: TextFieldState,
    onTriggerSearch: (String) -> Unit,
    onNavigate: (RepositoryInfo) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier.imePadding()) {
        SearchTextField(
            textState = textState,
            onTriggerSearch = onTriggerSearch,
            modifier = Modifier.testTag("edit"),
        )
        Box(
            modifier = Modifier.fillMaxSize(),
        ) {
            if (isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center),
                )
            }
            if (list.isEmpty()) {
                Text(
                    text = "empty",
                    style = MaterialTheme.typography.displayLarge,
                    modifier = Modifier.align(Alignment.Center),
                )
            } else {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    contentPadding = PaddingValues(4.dp),
                ) {
                    items(count = list.size, key = { list[it].hashCode() }) {
                        val item = list[it]
                        Text(
                            text = item.name,
                            style = MaterialTheme.typography.bodyLarge,
                            modifier = Modifier
                                .testTag("result")
                                .fillMaxWidth()
                                .clickable { onNavigate(item) },
                        )
                        if (it < list.lastIndex) {
                            HorizontalDivider()
                        }
                    }
                }
            }
        }
    }
}
