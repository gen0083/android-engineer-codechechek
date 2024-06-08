package jp.co.yumemi.codecheck.ui.detail

import cafe.adriel.voyager.core.model.ScreenModel
import jp.co.yumemi.codecheck.api.SearchClient
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.format
import kotlinx.datetime.format.Padding
import kotlinx.datetime.format.char
import kotlinx.datetime.toLocalDateTime

class RepositoryDetailScreenModel(
    private val searchClient: SearchClient,
) : ScreenModel {

    fun getLastSearchTime(): String {
        return searchClient
            .lastSearchDate
            .toLocalDateTime(TimeZone.currentSystemDefault())
            .format(
                LocalDateTime.Format {
                    year()
                    char('年')
                    monthNumber(Padding.SPACE)
                    char('月')
                    dayOfMonth(Padding.SPACE)
                    chars("日 ")
                    hour()
                    char(':')
                    minute()
                    char(':')
                    second()
                },
            )
    }
}
