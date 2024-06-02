/*
 * Copyright © 2021 YUMEMI Inc. All rights reserved.
 */
package jp.co.yumemi.android.codecheck

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import jp.co.yumemi.android.codecheck.api.RepositoryInfo
import jp.co.yumemi.android.codecheck.api.SearchClient
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.StateFlow
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class SearchViewModel(
    private val searchClient: SearchClient,
) : ViewModel() {
    val list: StateFlow<List<RepositoryInfo>>
        field = kotlinx.coroutines.flow.MutableStateFlow<List<RepositoryInfo>>(kotlin.collections.listOf())

    // 検索結果
    fun searchResults(inputText: String) {
        viewModelScope.async {
            list.value = searchClient.searchRepository(inputText)
        }
    }
}
