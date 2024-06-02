package jp.co.yumemi.android.codecheck

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.parameter
import io.ktor.client.statement.HttpResponse
import kotlinx.serialization.json.Json
import org.koin.core.annotation.Single
import java.util.Date

@Single
class SearchClient(
    private val client: HttpClient,
    private val json: Json,
) {
    var lastSearchDate = Date()
        private set

    suspend fun searchRepository(query: String): List<RepositoryInfo> {
        val response: HttpResponse = client.get("https://api.github.com/search/repositories") {
            header("Accept", "application/vnd.github.v3+json")
            parameter("q", query)
        }

        val jsonResponse = json.decodeFromString<RepositorySearchResponse>(response.body())
        lastSearchDate = Date()

        return jsonResponse.items
    }
}