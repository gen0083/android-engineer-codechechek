package jp.co.yumemi.android.codecheck

import android.app.Application
import jp.co.yumemi.android.codecheck.di.networkModule
import org.koin.core.context.startKoin

class TestApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(
                networkModule,
            )
        }
    }
}
