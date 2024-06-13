package jp.co.yumemi.codecheck.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import jp.co.yumemi.codecheck.ui.theme.AppTheme

@Preview
@PreviewLightDark
@Composable
private fun MyTopAppBarPreview() {
    AppTheme {
        MyTopAppBar(isRoot = false)
    }
}
