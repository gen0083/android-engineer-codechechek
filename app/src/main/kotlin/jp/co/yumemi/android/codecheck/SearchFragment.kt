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
import jp.co.yumemi.android.codecheck.databinding.FragmentSearchBinding
import kotlinx.coroutines.launch

class SearchFragment : Fragment(R.layout.fragment_search) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentSearchBinding.bind(view)

        val viewModel = SearchViewModel(requireContext().resources)

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
                        lifecycleScope.launch {
                            val result = viewModel.searchResults(inputText).await()
                            adapter.submitList(result)
                        }
                    }
                }
                return@setOnEditorActionListener true
            }

        binding.recyclerView.also {
            it.layoutManager = layoutManager
            it.addItemDecoration(dividerItemDecoration)
            it.adapter = adapter
        }
    }

    fun gotoRepositoryFragment(repositoryInfo: RepositoryInfo) {
        val action = SearchFragmentDirections
            .actionRepositoriesFragmentToRepositoryFragment(item = repositoryInfo)
        findNavController().navigate(action)
    }
}
