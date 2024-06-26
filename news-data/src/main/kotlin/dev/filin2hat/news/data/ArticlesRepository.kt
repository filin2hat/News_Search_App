package dev.filin2hat.news.data

import dev.filin2hat.database.NewsDataBase
import dev.filin2hat.database.entity.ArticleEntity
import dev.filin2hat.news.data.mappers.toArticle
import dev.filin2hat.news.data.mappers.toArticleEntity
import dev.filin2hat.news.data.models.Article
import dev.filin2hat.newsapi.NewsApi
import dev.filin2hat.newsapi.dto.ArticleDto
import dev.filin2hat.newsapi.dto.ResponseDto
import jakarta.inject.Inject
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.flow.onEach

/**
 * Репозиторий для получения flow с данными типа List<Article>
 *
 * @param dataBase локальная база данных
 * @param api API для получения данных от удаленного сервера
 */
class ArticlesRepository @Inject constructor(
    private val dataBase: NewsDataBase,
    private val api: NewsApi
) {
    /**
     * Функция получения всех новостей. Возвращает flow с данными типа RequestResult<List<Article>>
     *
     * @param mergeStrategy стратегия слияния данных
     */
    @OptIn(ExperimentalCoroutinesApi::class)
    fun getAllArticles(
        mergeStrategy: MergeStrategy<RequestResult<List<Article>>> = RequestResultMergeStrategy()
    ): Flow<RequestResult<List<Article>>> {
        val cachedArticles: Flow<RequestResult<List<Article>>> =
            getAllFromDataBase()

        val remoteArticles: Flow<RequestResult<List<Article>>> =
            getAllFromRemote()
        return cachedArticles
            .combine(remoteArticles, mergeStrategy::merge)
            .flatMapLatest { result ->
                if (result is RequestResult.Success) {
                    dataBase.articlesDao
                        .observeAll()
                        .map { entities -> entities.map { it.toArticle() } }
                        .map { RequestResult.Success(it) }
                } else {
                    flowOf(result)
                }
            }
    }

    /**
     * Функция поиска новостей. Возвращает flow с данными типа List<Article>
     */
    suspend fun searchArticles(query: String): Flow<Article> {
        api.everything()
        TODO("Not yet implemented")
    }

    private fun getAllFromRemote(): Flow<RequestResult<List<Article>>> {
        val apiRequestFlow =
            flow { emit(api.everything()) }
                .onEach { result ->
                    if (result.isSuccess) {
                        saveNetResponseToDataBase(result.getOrThrow().articles)
                    }
                }.map { it.toRequestResult() }
        val startFlow = flowOf<RequestResult<ResponseDto<ArticleDto>>>(RequestResult.InProgress())
        return merge(apiRequestFlow, startFlow)
            .map { result ->
                result.map { responseDto ->
                    responseDto.articles.map { it.toArticle() }
                }
            }
    }

    private suspend fun saveNetResponseToDataBase(articles: List<ArticleDto>) {
        val listArticleEntity = articles.map { it.toArticleEntity() }
        dataBase.articlesDao.insert(listArticleEntity)
    }

    private fun getAllFromDataBase(): Flow<RequestResult<List<Article>>> {
        val dbRequestFlow =
            flow { emit(dataBase.articlesDao.gatAll()) }
                .map { RequestResult.Success(it) }
        val startFlow = flowOf<RequestResult<List<ArticleEntity>>>(RequestResult.InProgress())
        return merge(dbRequestFlow, startFlow)
            .map { result ->
                result.map { articleEntityList ->
                    articleEntityList.map { it.toArticle() }
                }
            }
    }
}
