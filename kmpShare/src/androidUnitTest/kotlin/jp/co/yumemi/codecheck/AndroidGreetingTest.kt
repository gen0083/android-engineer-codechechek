package jp.co.yumemi.codecheck

import io.kotest.matchers.string.shouldContain
import org.junit.Test

class AndroidGreetingTest {

    @Test
    fun testExample() {
        Greeting().greet() shouldContain "Android"
    }
}
