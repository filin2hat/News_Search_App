package dev.filin2hat.newsapi

import androidx.annotation.IntRange
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.skydoves.retrofit.adapters.result.ResultCallAdapterFactory
import dev.filin2hat.newsapi.enums.Language
import dev.filin2hat.newsapi.enums.SortBy
import dev.filin2hat.newsapi.dto.ArticleDto
import dev.filin2hat.newsapi.dto.ResponseDto
import dev.filin2hat.newsapi.utils.NewsApiKeyInterceptor
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.Date

/**
 * [API documentation](https://newsapi.org/docs/get-started)
 */
interface NewsApi {

    @GET("/everything")
    suspend fun everything(
        @Query("q") query: String? = null,
        @Query("from") from: Date? = null,
        @Query("to") to: Date? = null,
        @Query("sortBy") sortBy: SortBy? = null,
        @Query("language") languages: List<Language>? = null,
        @Query("pageSize") @IntRange(from = 1, to = 100) pageSize: Int = 100,
        @Query("page") @IntRange(from = 1) page: Int = 1,
    ): Result<ResponseDto<ArticleDto>>
}

/**
 * API details [here](https://newsapi.org/docs/endpoints/everything)
 */
fun NewsApi(
    baseUrl: String,
    apiKey: String,
    okHttpClient: OkHttpClient? = null,
    json: Json = Json,
): NewsApi {
    return retrofit(baseUrl, apiKey, okHttpClient, json).create()
}

private fun retrofit(
    baseUrl: String,
    apiKey: String,
    okHttpClient: OkHttpClient?,
    json: Json,
): Retrofit {
    val jsonConverterFactory = json.asConverterFactory(MediaType.get("application/json"))

    val customOkHttpClient = (okHttpClient?.newBuilder() ?: OkHttpClient.Builder())
        .addInterceptor(NewsApiKeyInterceptor(apiKey))
        .build()

    return Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(jsonConverterFactory)
        .addCallAdapterFactory(ResultCallAdapterFactory.create())
        .client(customOkHttpClient)
        .build()
}
