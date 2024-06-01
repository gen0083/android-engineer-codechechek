package jp.co.yumemi.android.codecheck

import io.kotest.matchers.shouldBe
import io.ktor.client.HttpClient
import io.ktor.client.call.receive
import io.ktor.client.engine.android.Android
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.parameter
import io.ktor.client.statement.HttpResponse
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    private val client = HttpClient(Android)
    private val json = Json {
        ignoreUnknownKeys = true
    }
    @Test
    fun `response total count = 11`(): Unit = runBlocking {
        val response: HttpResponse = client.get("https://api.github.com/search/repositories") {
            header("Accept", "application/vnd.github.v3+json")
            parameter("q", "asdfghjkk")
        }

        val jsonBody = json.decodeFromString<RepositorySearchResponse>(response.receive())
        jsonBody.items.size shouldBe 11
    }

    @Test
    fun `response total count = 0`(): Unit = runBlocking {
        val response: HttpResponse = client.get("https://api.github.com/search/repositories") {
            header("Accept", "application/vnd.github.v3+json")
            parameter("q", "asdfghjkkkjlkjlkjiouore")
        }

        val str = response.receive<String>()
        println(str)
        val jsonBody = json.decodeFromString<RepositorySearchResponse>(str)
        jsonBody.items.size shouldBe 0
    }
}