package jp.co.yumemi.android.codecheck

import jp.co.yumemi.codecheck.Platform

class AndroidPlatform : Platform {
    override val name: String = "Android ${android.os.Build.VERSION.SDK_INT}"
}

actual fun getPlatform(): Platform = AndroidPlatform()
