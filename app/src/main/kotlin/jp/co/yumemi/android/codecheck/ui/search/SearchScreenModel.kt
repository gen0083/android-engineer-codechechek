/*
 * Copyright © 2021 YUMEMI Inc. All rights reserved.
 */
package jp.co.yumemi.android.codecheck.ui.search

import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import jp.co.yumemi.android.codecheck.api.RepositoryInfo
import jp.co.yumemi.android.codecheck.api.SearchClient
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.annotation.Singleton

@Singleton
class SearchScreenModel(
    private val searchClient: SearchClient,
) : StateScreenModel<SearchScreenModel.State>(State()) {

    data class State(
        val list: List<RepositoryInfo> = listOf(),
        val isLoading: Boolean = false,
    )

    // 検索結果
    fun searchResults(inputText: String) {
        if (inputText.isBlank()) return
        screenModelScope.launch {
            mutableState.update { it.copy(isLoading = true) }
            mutableState.update {
                it.copy(
                    list = searchClient.searchRepository(inputText),
                    isLoading = false,
                )
            }
        }
    }
}
