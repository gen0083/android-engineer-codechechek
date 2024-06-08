package jp.co.yumemi.codecheck.api

import io.kotest.matchers.shouldBe
import jp.co.yumemi.codecheck.httpClient
import kotlin.test.Test
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.Json
import org.koin.test.KoinTest

class SearchClientTest : KoinTest {
    private val client = SearchClient(
        httpClient(),
        Json {
            ignoreUnknownKeys = true
        },
    )

    @Test
    fun response_total_count_equal_11() = runTest {
        val actual = client.searchRepository("asdfghjkk")
        actual.size shouldBe 11
    }

    @Test
    fun response_total_count_equal_0() = runTest {
        val actual = client.searchRepository("asdfghjkkkjlkjlkjiouore")
        actual.size shouldBe 0
    }
}
