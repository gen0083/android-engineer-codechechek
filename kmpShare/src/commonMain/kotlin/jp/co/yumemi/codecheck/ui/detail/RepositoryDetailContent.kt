package jp.co.yumemi.codecheck.ui.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil3.ImageLoader
import coil3.compose.AsyncImage
import coil3.compose.LocalPlatformContext
import coil3.request.ImageRequest
import jp.co.yumemi.codecheck.api.RepositoryInfo
import jp.co.yumemi.codecheck.resources.Res
import jp.co.yumemi.codecheck.resources.count_forks
import jp.co.yumemi.codecheck.resources.count_open_issues
import jp.co.yumemi.codecheck.resources.count_stars
import jp.co.yumemi.codecheck.resources.count_watchers
import jp.co.yumemi.codecheck.resources.search_text
import jp.co.yumemi.codecheck.resources.written_language
import org.jetbrains.compose.resources.stringResource

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
            model = ImageRequest.Builder(LocalPlatformContext.current)
                .data(info.owner.ownerIconUrl)
                .build(),
            contentDescription = info.name,
            imageLoader = ImageLoader(LocalPlatformContext.current),
        )
        Text(text = info.name)
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(
                text = stringResource(
                    Res.string.written_language,
                    info.language ?: "unknown",
                ),
            )
            Column {
                Text(text = stringResource(Res.string.count_stars, info.stargazersCount))
                Text(text = stringResource(Res.string.count_watchers, info.watchersCount))
                Text(text = stringResource(Res.string.count_forks, info.forksCount))
                Text(text = stringResource(Res.string.count_open_issues, info.openIssuesCount))
            }
        }
        Box(
            modifier = Modifier.fillMaxHeight(),
        ) {
            Text(text = stringResource(Res.string.search_text, lastSearchDate))
        }
    }
}
