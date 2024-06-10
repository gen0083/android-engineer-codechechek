package jp.co.yumemi.android.codecheck

import android.text.TextUtils
import io.kotest.matchers.shouldBe
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class RobolectricSampleTest {
    @Test
    fun sampleTest() {
        TextUtils.isEmpty("abd") shouldBe false
    }
}
