package jp.co.yumemi.codecheck.ui.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
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
            Text(text = "test $userName")
        }
    }
}
