package jp.co.yumemi.android.codecheck.di

import io.ktor.client.HttpClient
import kotlinx.serialization.json.Json
import org.koin.dsl.module

val networkModule = module {
    single<HttpClient> {
        HttpClient()
    }
    single<Json> {
        Json {
            ignoreUnknownKeys = true
        }
    }
}
