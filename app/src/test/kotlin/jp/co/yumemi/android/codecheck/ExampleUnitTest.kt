package jp.co.yumemi.android.codecheck

import io.kotest.matchers.shouldBe
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.parameter
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import jp.co.yumemi.android.codecheck.di.networkModule
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.inject

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest : KoinTest {
    private val client by inject<HttpClient>()
    private val json by inject<Json>()

    @Before
    fun setup() {
        startKoin {
            modules(networkModule)
        }
    }

    @After
    fun tearDown() {
        stopKoin()
    }

    @Test
    fun `response total count = 11`(): Unit = runBlocking {
        val response: HttpResponse = client.get("https://api.github.com/search/repositories") {
            header("Accept", "application/vnd.github.v3+json")
            parameter("q", "asdfghjkk")
        }

        val jsonBody = json.decodeFromString<RepositorySearchResponse>(response.body())
        jsonBody.items.size shouldBe 11
    }

    @Test
    fun `response total count = 0`(): Unit = runBlocking {
        val response: HttpResponse = client.get("https://api.github.com/search/repositories") {
            header("Accept", "application/vnd.github.v3+json")
            parameter("q", "asdfghjkkkjlkjlkjiouore")
        }

        val str = response.bodyAsText()
        println(str)
        val jsonBody = json.decodeFromString<RepositorySearchResponse>(str)
        jsonBody.items.size shouldBe 0
    }
}