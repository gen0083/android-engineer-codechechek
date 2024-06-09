package jp.co.yumemi.codecheck

import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.js.JsClient

private val platform = object : Platform {
    override val name: String
        get() = "Kotlin/JS it's JavaScript!?"
}

actual fun httpClient(config: HttpClientConfig<*>.() -> Unit): HttpClient = HttpClient(JsClient())

actual fun getPlatform(): Platform = platform
