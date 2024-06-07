package jp.co.yumemi.codecheck.api

import io.kotest.matchers.shouldBe
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import kotlin.test.Test
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json
import org.koin.test.KoinTest

class SearchClientTest : KoinTest {
    private val client = SearchClient(
        HttpClient(CIO),
        Json { ignoreUnknownKeys = true },
    )

    @Test
    fun `response_total_count=11`(): Unit = runBlocking {
        val actual = client.searchRepository("asdfghjkk")
        actual.size shouldBe 11
    }

    @Test
    fun `response_total_count=0`(): Unit = runBlocking {
        val actual = client.searchRepository("asdfghjkkkjlkjlkjiouore")
        actual.size shouldBe 0
    }
}
