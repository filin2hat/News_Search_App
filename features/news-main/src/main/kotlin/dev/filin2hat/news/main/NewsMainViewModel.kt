package dev.filin2hat.news.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.filin2hat.news.data.RequestResult
import dev.filin2hat.news.main.models.ArticleUIModel
import dev.filin2hat.news.main.usecase.GetAllArticlesUseCase
import jakarta.inject.Provider
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
internal class NewsMainViewModel @Inject constructor(
    getAllArticlesUseCase: Provider<GetAllArticlesUseCase>
) : ViewModel() {
    private val state: StateFlow<State> =
        getAllArticlesUseCase.get().invoke()
            .map { it.toState() }
            .stateIn(viewModelScope, SharingStarted.Lazily, State.Empty)
}

private fun RequestResult<List<ArticleUIModel>>.toState(): State = when (this) {
    is RequestResult.Success -> State.Success(data)
    is RequestResult.Error -> State.Error(data)
    is RequestResult.InProgress -> State.Loading(data)
}

sealed class State {
    data object Empty : State()

    data class Loading(val articles: List<ArticleUIModel>? = null) : State()

    data class Error(val articles: List<ArticleUIModel>? = null) : State()

    data class Success(val articles: List<ArticleUIModel>) : State()
}
