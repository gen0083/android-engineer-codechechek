package jp.co.yumemi.codecheck.ui.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.bottomSheet.LocalBottomSheetNavigator

data class RepositoryListScreen(
    val userName: String,
) : Screen {
    @ExperimentalMaterial3Api
    @Composable
    override fun Content() {
        val bottomNavigator = LocalBottomSheetNavigator.current

        Column(
            modifier = Modifier
                .statusBarsPadding()
                .navigationBarsPadding(),
        ) {
            IconButton(
                onClick = { bottomNavigator.hide() },
            ) {
                Icon(imageVector = Icons.Default.Close, contentDescription = "close")
            }
            Text(text = "test $userName")
        }
    }
}
