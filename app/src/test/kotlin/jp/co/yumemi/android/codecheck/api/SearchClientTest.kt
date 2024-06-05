package jp.co.yumemi.android.codecheck.api

import io.kotest.matchers.shouldBe
import jp.co.yumemi.android.codecheck.di.ScanModule
import jp.co.yumemi.android.codecheck.di.networkModule
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.ksp.generated.module
import org.koin.test.KoinTest
import org.koin.test.inject

class SearchClientTest : KoinTest {
    private val client by inject<SearchClient>()

    @Before
    fun setup() {
        startKoin {
            modules(
                ScanModule().module,
                networkModule,
            )
        }
    }

    @After
    fun tearDown() {
        stopKoin()
    }

    @Test
    fun `response total count = 11`(): Unit = runBlocking {
        val actual = client.searchRepository("asdfghjkk")
        actual.size shouldBe 11
    }

    @Test
    fun `response total count = 0`(): Unit = runBlocking {
        val actual = client.searchRepository("asdfghjkkkjlkjlkjiouore")
        actual.size shouldBe 0
    }
}
