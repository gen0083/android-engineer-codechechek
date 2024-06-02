package jp.co.yumemi.android.codecheck

import android.app.Application
import jp.co.yumemi.android.codecheck.di.ScanModule
import jp.co.yumemi.android.codecheck.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.ksp.generated.module

class CodeCheckApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@CodeCheckApp)
            modules(
                ScanModule().module,
                networkModule,
            )
        }
    }
}