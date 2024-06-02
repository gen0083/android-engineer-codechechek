/*
 * Copyright © 2021 YUMEMI Inc. All rights reserved.
 */
package jp.co.yumemi.android.codecheck

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.parameter
import io.ktor.client.statement.HttpResponse
import jp.co.yumemi.android.codecheck.TopActivity.Companion.lastSearchDate
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import org.koin.android.annotation.KoinViewModel
import java.util.Date

@KoinViewModel
class SearchViewModel(
    private val client: HttpClient,
    private val json: Json,
) : ViewModel() {
    val list: StateFlow<List<RepositoryInfo>>
        field = kotlinx.coroutines.flow.MutableStateFlow<List<RepositoryInfo>>(kotlin.collections.listOf())

    // 検索結果
    fun searchResults(inputText: String) {
        viewModelScope.launch {
            val response: HttpResponse = client.get("https://api.github.com/search/repositories") {
                header("Accept", "application/vnd.github.v3+json")
                parameter("q", inputText)
            }

            val jsonResponse = json.decodeFromString<RepositorySearchResponse>(response.body())
            lastSearchDate = Date()

            list.value = jsonResponse.items
        }
    }
}
