package jp.co.yumemi.android.codecheck

import android.app.Application
import jp.co.yumemi.codecheck.koin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger

class CodeCheckApp : Application() {

    override fun onCreate() {
        super.onCreate()
        koin.apply {
            androidLogger()
            androidContext(this@CodeCheckApp)
        }
    }
}
