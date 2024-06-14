package dev.filin2hat.news.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.filin2hat.news.data.RequestResult
import dev.filin2hat.news.main.models.ArticleUIModel
import dev.filin2hat.news.main.usecase.GetAllArticlesUseCase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

internal class NewsMainViewModel(
    private val getAllArticlesUseCase: GetAllArticlesUseCase,
) : ViewModel() {
    private val state: StateFlow<State> =
        getAllArticlesUseCase()
            .map { it.toState() }
            .stateIn(viewModelScope, SharingStarted.Lazily, State.Empty)
}

private fun RequestResult<List<ArticleUIModel>>.toState(): State =
    when (this) {
        is RequestResult.Success -> State.Success(data)
        is RequestResult.Error -> State.Error(data)
        is RequestResult.InProgress -> State.Loading(data)
    }

sealed class State {
    data object Empty : State()

    data class Loading(
        val articles: List<ArticleUIModel>? = null,
    ) : State()

    data class Error(
        val articles: List<ArticleUIModel>? = null,
    ) : State()

    data class Success(
        val articles: List<ArticleUIModel>,
    ) : State()
}
