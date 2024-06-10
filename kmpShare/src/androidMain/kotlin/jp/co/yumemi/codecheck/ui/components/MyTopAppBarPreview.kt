package jp.co.yumemi.codecheck.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import jp.co.yumemi.codecheck.ui.theme.AppTheme

@Preview
@Composable
private fun MyTopAppBarPreview() {
    AppTheme {
        MyTopAppBar(isRoot = false)
    }
}
