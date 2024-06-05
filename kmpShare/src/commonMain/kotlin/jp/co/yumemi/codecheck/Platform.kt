package jp.co.yumemi.codecheck

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform