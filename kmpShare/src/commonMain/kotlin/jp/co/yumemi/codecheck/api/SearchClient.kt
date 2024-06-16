package jp.co.yumemi.codecheck.api

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.parameter
import io.ktor.client.statement.HttpResponse
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.serialization.json.Json

class SearchClient(
    private val client: HttpClient,
    private val json: Json,
) {
    var lastSearchDate: Instant = Clock.System.now()
        private set

    suspend fun searchRepository(query: String): List<RepositoryInfo> {
        val response: HttpResponse = client.get("https://api.github.com/search/repositories") {
            header("Accept", "application/vnd.github.v3+json")
            parameter("q", query)
        }

        val jsonResponse = json.decodeFromString<RepositorySearchResponse>(response.body())
        lastSearchDate = Clock.System.now()

        return jsonResponse.items
    }

    suspend fun searchRepositoriesByUser(username: String): List<RepositoryInfo> {
        val response = client.get("https://api.github.com/users/$username/repos") {
            header("Accept", "application/vnd.github.v3+json")
        }
        val jsonResponse = json.decodeFromString<List<RepositoryInfo>>(response.body())
        return jsonResponse
    }
}
