package dev.filin2hat.news.main.usecase

import dev.filin2hat.news.data.ArticlesRepository
import dev.filin2hat.news.data.models.Article
import kotlinx.coroutines.flow.Flow

class GetAllArticlesUseCase(
    private val repository: ArticlesRepository
) {
    operator fun invoke(): Flow<List<Article>> {
        return repository.getAllArticles()
    }
}
