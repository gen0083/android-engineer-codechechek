/*
 * Copyright © 2021 YUMEMI Inc. All rights reserved.
 */
package jp.co.yumemi.android.codecheck

import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import jp.co.yumemi.android.codecheck.adapter.CustomAdapter
import jp.co.yumemi.android.codecheck.adapter.OnItemClickListener
import jp.co.yumemi.android.codecheck.databinding.FragmentSearchBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchFragment : Fragment(R.layout.fragment_search) {
    private val viewModel: SearchViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentSearchBinding.bind(view)

        val layoutManager = LinearLayoutManager(requireContext())
        val dividerItemDecoration =
            DividerItemDecoration(requireContext(), layoutManager.orientation)
        val adapter = CustomAdapter(object : OnItemClickListener {
            override fun itemClick(item: RepositoryInfo) {
                gotoRepositoryFragment(item)
            }
        })

        binding.searchInputText
            .setOnEditorActionListener { editText, action, _ ->
                if (action == EditorInfo.IME_ACTION_SEARCH) {
                    val inputText = editText.text.toString()
                    if (inputText.isBlank()) {
                        editText.error = "文字を入力してください"
                    } else {
                        viewModel.searchResults(inputText)
                    }
                }
                return@setOnEditorActionListener true
            }

        binding.recyclerView.also {
            it.layoutManager = layoutManager
            it.addItemDecoration(dividerItemDecoration)
            it.adapter = adapter
        }

        lifecycleScope.launch {
            viewModel.list.collectLatest {
                // TODO: emptyやらindicatorやら表示
                adapter.submitList(it)
            }
        }
    }

    fun gotoRepositoryFragment(repositoryInfo: RepositoryInfo) {
        val action = SearchFragmentDirections
            .actionRepositoriesFragmentToRepositoryFragment(item = repositoryInfo)
        findNavController().navigate(action)
    }
}
