package jp.co.yumemi.android.codecheck

import androidx.test.platform.app.InstrumentationRegistry
import io.kotest.matchers.shouldBe
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.parameter
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import jp.co.yumemi.android.codecheck.api.RepositorySearchResponse
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json
import org.junit.Assert.assertEquals
import org.junit.Test
import org.koin.java.KoinJavaComponent.inject

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleInstrumentedTest {
    private val client by inject<HttpClient>(HttpClient::class.java)
    private val json by inject<Json>(Json::class.java)

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("jp.co.yumemi.android.codecheck", appContext.packageName)
    }

    @Test
    fun getClientTest(): Unit = runBlocking {
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