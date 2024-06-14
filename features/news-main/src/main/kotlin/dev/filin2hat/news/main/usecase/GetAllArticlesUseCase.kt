package dev.filin2hat.news.main.usecase

import dev.filin2hat.news.data.ArticlesRepository
import dev.filin2hat.news.data.RequestResult
import dev.filin2hat.news.data.map
import dev.filin2hat.news.main.mappers.toArticleUIModel
import dev.filin2hat.news.main.models.ArticleUIModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetAllArticlesUseCase(
    private val repository: ArticlesRepository,
) {
    operator fun invoke(): Flow<RequestResult<List<ArticleUIModel>>> =
        repository
            .getAllArticles()
            .map { requestResult ->
                requestResult.map { articles ->
                    articles.map { it.toArticleUIModel() }
                }
            }
}
