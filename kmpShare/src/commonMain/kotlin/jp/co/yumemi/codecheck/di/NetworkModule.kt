package jp.co.yumemi.codecheck.di

import io.ktor.client.HttpClient
import jp.co.yumemi.codecheck.api.SearchClient
import jp.co.yumemi.codecheck.httpClient
import kotlinx.serialization.json.Json
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val networkModule = module {
    single<HttpClient> {
        httpClient()
    }
    single<Json> {
        Json {
            ignoreUnknownKeys = true
        }
    }
    singleOf(::SearchClient)
}
