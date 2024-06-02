/*
 * Copyright © 2021 YUMEMI Inc. All rights reserved.
 */
package jp.co.yumemi.android.codecheck

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import coil.load
import jp.co.yumemi.android.codecheck.api.SearchClient
import jp.co.yumemi.android.codecheck.databinding.FragmentDetailBinding
import org.koin.android.ext.android.inject

class RepositoryDetailFragment : Fragment(R.layout.fragment_detail) {

    private val args: RepositoryDetailFragmentArgs by navArgs()

    private lateinit var binding: FragmentDetailBinding
    private val searchClient by inject<SearchClient>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentDetailBinding.bind(view)

        val item = args.item

        binding.ownerIconView.load(item.owner.ownerIconUrl)
        binding.nameView.text = item.name
        binding.languageView.text = getString(R.string.written_language, item.language ?: "unknown")
        binding.starsView.text = getString(R.string.count_stars, item.stargazersCount)
        binding.watchersView.text = getString(R.string.count_watchers, item.watchersCount)
        binding.forksView.text = getString(R.string.count_forks, item.forksCount)
        binding.openIssuesView.text = getString(R.string.count_open_issues, item.openIssuesCount)
        binding.searchDate.text =
            getString(R.string.search_text, searchClient.lastSearchDate.toString())
    }
}
