/*
 * Copyright © 2021 YUMEMI Inc. All rights reserved.
 */
package jp.co.yumemi.android.codecheck

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
import kotlinx.serialization.json.Json
import java.util.Date

class SearchViewModel : ViewModel() {
    private val json = Json {
        ignoreUnknownKeys = true
    }

    // 検索結果
    suspend fun searchResults(inputText: String): Deferred<List<RepositoryInfo>> =
        viewModelScope.async {
            val client = HttpClient(Android)

            val response: HttpResponse = client.get("https://api.github.com/search/repositories") {
                header("Accept", "application/vnd.github.v3+json")
                parameter("q", inputText)
            }

            val jsonResponse = json.decodeFromString<RepositorySearchResponse>(response.receive())
            lastSearchDate = Date()

            return@async jsonResponse.items
        }
}
