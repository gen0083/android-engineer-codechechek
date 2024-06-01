/*
 * Copyright © 2021 YUMEMI Inc. All rights reserved.
 */
package jp.co.yumemi.android.codecheck

import android.content.res.Resources
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.ktor.client.HttpClient
import io.ktor.client.call.receive
import io.ktor.client.engine.android.Android
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.parameter
import io.ktor.client.statement.HttpResponse
import jp.co.yumemi.android.codecheck.TopActivity.Companion.lastSearchDate
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import org.json.JSONArray
import org.json.JSONObject
import java.util.Date

class SearchViewModel(
    private val resources: Resources,
) : ViewModel() {

    // 検索結果
    suspend fun searchResults(inputText: String): Deferred<List<RepositoryInfo>> =
        viewModelScope.async {
        val client = HttpClient(Android)

        val response: HttpResponse = client.get("https://api.github.com/search/repositories") {
            header("Accept", "application/vnd.github.v3+json")
            parameter("q", inputText)
        }

        val jsonBody = JSONObject(response.receive<String>())

        val jsonItems = jsonBody.optJSONArray("items") ?: JSONArray()

            val repositoryInfos = mutableListOf<RepositoryInfo>()

        for (i in 0 until jsonItems.length()) {
            val jsonItem = jsonItems.optJSONObject(i) ?: continue
            val name = jsonItem.optString("full_name")
            val ownerIconUrl = jsonItem.optJSONObject("owner")?.optString("avatar_url") ?: ""
            val language = jsonItem.optString("language")
            val stargazersCount = jsonItem.optLong("stargazers_count")
            val watchersCount = jsonItem.optLong("watchers_count")
            val forksCount = jsonItem.optLong("forks_count")
            val openIssuesCount = jsonItem.optLong("open_issues_count")

            repositoryInfos.add(
                RepositoryInfo(
                    name = name,
                    ownerIconUrl = ownerIconUrl,
                    language = resources.getString(R.string.written_language, language),
                    stargazersCount = stargazersCount,
                    watchersCount = watchersCount,
                    forksCount = forksCount,
                    openIssuesCount = openIssuesCount
                )
            )
        }

        lastSearchDate = Date()

            return@async repositoryInfos.toList()
    }
}
