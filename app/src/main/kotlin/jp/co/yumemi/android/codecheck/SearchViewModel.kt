/*
 * Copyright © 2021 YUMEMI Inc. All rights reserved.
 */
package jp.co.yumemi.android.codecheck

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import jp.co.yumemi.android.codecheck.api.RepositoryInfo
import jp.co.yumemi.android.codecheck.api.SearchClient
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class SearchViewModel(
    private val searchClient: SearchClient,
) : ViewModel() {
    val list: StateFlow<List<RepositoryInfo>>
        field = kotlinx.coroutines.flow.MutableStateFlow<List<RepositoryInfo>>(
            kotlin.collections.listOf(),
        )
    val isLoading: State<Boolean>
        field = androidx.compose.runtime.mutableStateOf(false)

    // 検索結果
    fun searchResults(inputText: String) {
        if (inputText.isBlank()) return
        viewModelScope.launch {
            isLoading.value = true
            list.value = searchClient.searchRepository(inputText)
            isLoading.value = false
        }
    }
}
