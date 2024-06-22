package dev.filin2hat.newssearch

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.filin2hat.database.NewsDataBase
import dev.filin2hat.newsapi.NewsApi
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideNewsApi(): NewsApi = NewsApi(
        baseUrl = BuildConfig.NEWS_API_BASE_URL,
        apiKey = BuildConfig.NEWS_API_KEY
    )

    @Provides
    @Singleton
    fun provideNewsDataBase(@ApplicationContext context: Context): NewsDataBase = NewsDataBase(
        context
    )
}
