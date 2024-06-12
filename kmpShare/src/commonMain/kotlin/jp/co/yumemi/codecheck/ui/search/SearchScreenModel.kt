/*
 * Copyright © 2021 YUMEMI Inc. All rights reserved.
 */
package jp.co.yumemi.codecheck.ui.search

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.text2.input.TextFieldState
import androidx.compose.foundation.text2.input.textAsFlow
import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import jp.co.yumemi.codecheck.api.RepositoryInfo
import jp.co.yumemi.codecheck.api.SearchClient
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@FlowPreview
@ExperimentalFoundationApi
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

    fun connectTextFieldState(state: TextFieldState) {
        screenModelScope.launch {
            state.textAsFlow()
                .debounce(1000)
                .distinctUntilChanged { old, new -> old.toString() == new.toString() }
                .collectLatest {
                    searchResults(it.toString())
                }
        }
    }
}
