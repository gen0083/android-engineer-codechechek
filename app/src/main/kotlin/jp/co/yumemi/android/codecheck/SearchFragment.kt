/*
 * Copyright © 2021 YUMEMI Inc. All rights reserved.
 */
package jp.co.yumemi.android.codecheck

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import jp.co.yumemi.android.codecheck.api.RepositoryInfo
import jp.co.yumemi.android.codecheck.ui.SearchContent
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchFragment : Fragment() {
    private val viewModel: SearchViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return ComposeView(requireContext()).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                val list by viewModel.list.collectAsState()
                val isLoading by viewModel.isLoading
                MaterialTheme {
                    SearchContent(
                        list = list,
                        isLoading = isLoading,
                        onTextChanged = viewModel::searchResults,
                        onNavigate = ::gotoRepositoryFragment,
                    )
                }
            }
        }
    }

    private fun gotoRepositoryFragment(repositoryInfo: RepositoryInfo) {
        val action = SearchFragmentDirections
            .actionRepositoriesFragmentToRepositoryFragment(item = repositoryInfo)
        findNavController().navigate(action)
    }
}
