package jp.co.yumemi.codecheck.api

import io.kotest.matchers.shouldBe
import jp.co.yumemi.codecheck.httpClient
import kotlin.test.Test
import kotlinx.coroutines.runBlocking
import org.koin.test.KoinTest

class SearchClientTest : KoinTest {
    private val client = httpClient()

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
