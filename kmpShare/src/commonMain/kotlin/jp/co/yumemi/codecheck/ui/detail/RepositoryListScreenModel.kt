package jp.co.yumemi.codecheck.ui.detail

import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import jp.co.yumemi.codecheck.api.RepositoryInfo
import jp.co.yumemi.codecheck.api.SearchClient
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RepositoryListScreenModel(
    private val searchClient: SearchClient,
    private val userName: String,
) : StateScreenModel<RepositoryListScreenModel.State>(State.Loading) {

    sealed class State {
        data object Loading : State()

        data class Success(
            val repositories: List<RepositoryInfo>,
        ) : State()

        data class Error(
            val throwable: Throwable? = null,
        ) : State()
    }

    init {
        load()
    }

    fun load() {
        screenModelScope.launch {
            mutableState.update { State.Loading }
            kotlin.runCatching {
                searchClient.searchRepositoriesByUser(userName)
            }
                .onFailure {
                    mutableState.value = State.Error(it)
                }
                .onSuccess {
                    mutableState.value = State.Success(
                        repositories = it,
                    )
                }
        }
    }
}
