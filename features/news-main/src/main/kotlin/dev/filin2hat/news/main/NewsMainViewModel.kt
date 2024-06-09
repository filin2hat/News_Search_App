package dev.filin2hat.news.main

import androidx.lifecycle.ViewModel
import dev.filin2hat.news.main.models.ArticleModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

internal class NewsMainViewModel : ViewModel() {

    private val _state = MutableStateFlow(State.Empty)
    val state: StateFlow<State>
        get() = _state.asStateFlow()
}

sealed class State {
    data object Empty : State()
    data object Loading : State()
    data class Success(val articles: List<ArticleModel>) : State()
    data class Error(val errorMessage: String) : State()
}
