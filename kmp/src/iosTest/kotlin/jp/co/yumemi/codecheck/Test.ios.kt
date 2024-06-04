package jp.co.yumemi.codecheck

import io.kotest.matchers.string.shouldContain
import kotlin.test.Test

class IosGreetingTest {

    @Test
    fun testExample() {
        Greeting().greet() shouldContain "iOS"
    }
}
