package dev.filin2hat.news.data

import dev.filin2hat.database.NewsDataBase
import dev.filin2hat.news.data.mappers.toArticle
import dev.filin2hat.news.data.models.Article
import dev.filin2hat.newsapi.NewsApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ArticlesRepository(
    private val dataBase: NewsDataBase,
    private val api: NewsApi,
) {
    fun getAllArticles(): RequestResult<Flow<List<Article>>> {
        return RequestResult.InProgress(
            dataBase.articlesDao
                .gatAll()
                .map { articleDto ->
                    articleDto.map { it.toArticle() }
                }
        )
    }

    suspend fun searchArticles(query: String): Flow<Article> {
        api.everything()
        TODO("Not yet implemented")
    }
}

sealed class RequestResult<E>(protected  val data: E?) {

    class InProgress<E>(data: E?) : RequestResult<E>(data)
    class Success<E>(data: E?) : RequestResult<E>(data)
    class Error<E>(data: E?) : RequestResult<E>(data)
}
