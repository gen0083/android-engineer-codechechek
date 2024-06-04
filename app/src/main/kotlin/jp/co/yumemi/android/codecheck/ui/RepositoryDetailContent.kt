package jp.co.yumemi.android.codecheck.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import jp.co.yumemi.android.codecheck.R
import jp.co.yumemi.android.codecheck.api.RepositoryInfo
import jp.co.yumemi.android.codecheck.api.RepositoryOwner

@Composable
fun RepositoryDetailContent(
    info: RepositoryInfo,
    lastSearchDate: String,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        AsyncImage(
            model = info.owner.ownerIconUrl,
            contentDescription = info.name,
        )
        Text(text = info.name)
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(
                text = stringResource(
                    R.string.written_language,
                    info.language ?: "unknown",
                ),
            )
            Column {
                Text(text = stringResource(R.string.count_stars, info.stargazersCount))
                Text(text = stringResource(R.string.count_watchers, info.watchersCount))
                Text(text = stringResource(R.string.count_forks, info.forksCount))
                Text(text = stringResource(R.string.count_open_issues, info.openIssuesCount))
            }
        }
        Box(
            modifier = Modifier.fillMaxHeight(),
            contentAlignment = Alignment.BottomCenter,
        ) {
            Text(text = stringResource(R.string.search_text, lastSearchDate))
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun RepositoryDetailContentPreview() {
    MaterialTheme {
        RepositoryDetailContent(
            info = RepositoryInfo(
                name = "test",
                owner = RepositoryOwner(
                    ownerIconUrl = "https://avatars.githubusercontent.com/u/7608725?v=4",
                ),
                language = null,
                stargazersCount = 0,
                watchersCount = 1,
                forksCount = 2,
                openIssuesCount = 3,
            ),
            lastSearchDate = "1/1/2024",
        )
    }
}
