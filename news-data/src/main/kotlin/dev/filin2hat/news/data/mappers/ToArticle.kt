package dev.filin2hat.news.data.mappers

import dev.filin2hat.database.entity.ArticleEntity
import dev.filin2hat.database.entity.SourceEntity
import dev.filin2hat.news.data.models.Article
import dev.filin2hat.news.data.models.Source
import dev.filin2hat.newsapi.dto.ArticleDto
import dev.filin2hat.newsapi.dto.SourceDto

internal fun ArticleEntity.toArticle(): Article =
    Article(
        id = this.id,
        author = this.author,
        title = this.title,
        description = this.description,
        url = this.url,
        urlToImage = this.urlToImage,
        publishedAt = this.publishedAt,
        content = this.content,
        source = this.source.toSource(),
    )

private fun SourceEntity.toSource(): Source =
    Source(
        id = this.id,
        name = this.name,
    )

internal fun ArticleDto.toArticle(): Article =
    Article(
        source = this.sourceDto.toSource(),
        author = this.author,
        title = this.title,
        description = this.description,
        url = this.url,
        urlToImage = this.urlToImage,
        publishedAt = this.publishedAt,
        content = this.content,
        id = null,
    )

private fun SourceDto.toSource(): Source =
    Source(
        id = this.id,
        name = this.name,
    )
