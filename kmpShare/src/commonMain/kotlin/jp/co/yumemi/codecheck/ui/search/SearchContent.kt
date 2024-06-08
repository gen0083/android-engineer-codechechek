package jp.co.yumemi.codecheck.ui.search

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text2.BasicTextField2
import androidx.compose.foundation.text2.input.TextFieldLineLimits
import androidx.compose.foundation.text2.input.rememberTextFieldState
import androidx.compose.foundation.text2.input.textAsFlow
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import jp.co.yumemi.codecheck.api.RepositoryInfo
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SearchContent(
    list: List<RepositoryInfo>,
    isLoading: Boolean,
    onTextChanged: (String) -> Unit,
    onNavigate: (RepositoryInfo) -> Unit,
    modifier: Modifier = Modifier,
) {
    val textState = rememberTextFieldState("")
    LaunchedEffect(Unit) {
        this.launch {
            textState.textAsFlow().debounce(1000).collect {
                onTextChanged(it.toString())
            }
        }
    }
    Column(modifier = modifier) {
        BasicTextField2(
            state = textState,
            lineLimits = TextFieldLineLimits.SingleLine,
            keyboardActions = KeyboardActions(
                onSend = { onTextChanged(textState.text.toString()) },
                onSearch = { onTextChanged(textState.text.toString()) },
                onDone = { onTextChanged(textState.text.toString()) },
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .border(
                    border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
                    shape = RoundedCornerShape(4.dp),
                )
                .padding(8.dp),
            textStyle = TextStyle(fontSize = 20.sp),
        )
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            if (isLoading) {
                CircularProgressIndicator()
            }
            if (list.isEmpty()) {
                Text(text = "empty")
            } else {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    contentPadding = PaddingValues(4.dp),
                    modifier = Modifier.imePadding(),
                ) {
                    items(count = list.size, key = { list[it].hashCode() }) {
                        val item = list[it]
                        Text(
                            text = item.name,
                            fontSize = 12.sp,
                            modifier = Modifier
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
