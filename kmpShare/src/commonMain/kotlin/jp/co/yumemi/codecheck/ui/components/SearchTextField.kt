package jp.co.yumemi.codecheck.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text2.BasicTextField2
import androidx.compose.foundation.text2.TextFieldDecorator
import androidx.compose.foundation.text2.input.TextFieldLineLimits
import androidx.compose.foundation.text2.input.TextFieldState
import androidx.compose.foundation.text2.input.clearText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.LastBaseline
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import jp.co.yumemi.codecheck.resources.Res
import jp.co.yumemi.codecheck.resources.searchInputText_hint
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SearchTextField(
    textState: TextFieldState,
    onTriggerSearch: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    BasicTextField2(
        state = textState,
        lineLimits = TextFieldLineLimits.SingleLine,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
        keyboardActions = KeyboardActions(
            onSend = { onTriggerSearch(textState.text.toString()) },
            onSearch = { onTriggerSearch(textState.text.toString()) },
            onDone = { onTriggerSearch(textState.text.toString()) },
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
                            contentDescription = "clear",
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
