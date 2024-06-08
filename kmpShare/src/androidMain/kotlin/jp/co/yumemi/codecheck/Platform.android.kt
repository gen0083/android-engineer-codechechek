package jp.co.yumemi.codecheck

import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig

class AndroidPlatform : Platform {
    override val name: String = "Android ${android.os.Build.VERSION.SDK_INT}"
}

actual fun httpClient(config: HttpClientConfig<*>.() -> Unit): HttpClient = HttpClient {
    config(this)
}

actual fun getPlatform(): Platform = AndroidPlatform()
