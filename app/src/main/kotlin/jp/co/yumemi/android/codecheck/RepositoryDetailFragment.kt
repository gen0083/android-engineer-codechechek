/*
 * Copyright © 2021 YUMEMI Inc. All rights reserved.
 */
package jp.co.yumemi.android.codecheck

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import jp.co.yumemi.android.codecheck.api.SearchClient
import jp.co.yumemi.android.codecheck.ui.RepositoryDetailContent
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.format
import kotlinx.datetime.format.Padding
import kotlinx.datetime.format.char
import kotlinx.datetime.toLocalDateTime
import org.koin.android.ext.android.inject

class RepositoryDetailFragment : Fragment() {

    private val args: RepositoryDetailFragmentArgs by navArgs()
    private val searchClient by inject<SearchClient>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        return ComposeView(requireContext()).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                MaterialTheme {
                    RepositoryDetailContent(
                        info = args.item,
                        lastSearchDate = searchClient
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
                            ),
                    )
                }
            }
        }
    }
}
