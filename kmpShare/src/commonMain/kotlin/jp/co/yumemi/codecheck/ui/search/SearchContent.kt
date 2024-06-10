package jp.co.yumemi.codecheck.ui.search

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text2.BasicTextField2
import androidx.compose.foundation.text2.TextFieldDecorator
import androidx.compose.foundation.text2.input.TextFieldLineLimits
import androidx.compose.foundation.text2.input.TextFieldState
import androidx.compose.foundation.text2.input.clearText
import androidx.compose.foundation.text2.input.rememberTextFieldState
import androidx.compose.foundation.text2.input.textAsFlow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.LastBaseline
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import jp.co.yumemi.codecheck.api.RepositoryInfo
import jp.co.yumemi.codecheck.resources.Res
import jp.co.yumemi.codecheck.resources.searchInputText_hint
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.stringResource

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
    Column(modifier = modifier.imePadding()) {
        SearchTextField(
            textState = textState,
            onTextChanged = onTextChanged,
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

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SearchTextField(
    textState: TextFieldState,
    onTextChanged: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    BasicTextField2(
        state = textState,
        lineLimits = TextFieldLineLimits.SingleLine,
        keyboardActions = KeyboardActions(
            onSend = { onTextChanged(textState.text.toString()) },
            onSearch = { onTextChanged(textState.text.toString()) },
            onDone = { onTextChanged(textState.text.toString()) },
        ),
        decorator = TextFieldDecorator { innerTextField ->
            Row(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.background)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "",
                    tint = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier.minimumInteractiveComponentSize(),
                )
                Box(modifier = Modifier.weight(1f)) {
                    innerTextField()
                    if (textState.text.isBlank()) {
                        Text(
                            text = stringResource(Res.string.searchInputText_hint),
                            color = MaterialTheme.colorScheme.onBackground,
                            style = TextStyle(
                                color = MaterialTheme.colorScheme.onBackground,
                                fontSize = 20.sp,
                            ),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                        )
                    }
                }
                if (textState.text.isNotBlank()) {
                    IconButton(
                        onClick = {
                            textState.clearText()
                        },
                        colors = IconButtonDefaults.iconButtonColors(),
                        modifier = Modifier
                            .alignBy(LastBaseline),
                    ) {
                        Icon(
                            imageVector = Icons.Default.Clear,
                            contentDescription = "delete",
                            tint = MaterialTheme.colorScheme.onBackground,
                        )
                    }
                }
            }
        },
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
            .border(
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
                shape = RoundedCornerShape(4.dp),
            )
            .padding(8.dp),
        textStyle = TextStyle(
            color = MaterialTheme.colorScheme.onBackground,
            fontSize = 20.sp,
        ),
        cursorBrush = SolidColor(MaterialTheme.colorScheme.onBackground),
    )
}
