package dev.filin2hat.news.data.mappers

import dev.filin2hat.database.entity.ArticleEntity
import dev.filin2hat.database.entity.SourceEntity
import dev.filin2hat.news.data.models.Article
import dev.filin2hat.news.data.models.Source
import dev.filin2hat.newsapi.dto.ArticleDto
import dev.filin2hat.newsapi.dto.SourceDto

internal fun Article.toArticleEntity(): ArticleEntity = ArticleEntity(
    id = this.id ?: 0,
    author = this.author,
    title = this.title,
    description = this.description,
    url = this.url,
    urlToImage = this.urlToImage,
    publishedAt = this.publishedAt,
    content = this.content,
    source = this.source.toSourceEntity()
)

private fun Source.toSourceEntity(): SourceEntity = SourceEntity(
    id = this.id,
    name = this.name
)

internal fun ArticleDto.toArticleEntity(): ArticleEntity = ArticleEntity(
    source = this.sourceDto.toSourceEntity(),
    author = this.author,
    title = this.title,
    description = this.description,
    url = this.url,
    urlToImage = this.urlToImage,
    publishedAt = this.publishedAt,
    content = this.content,
    id = null
)

private fun SourceDto.toSourceEntity(): SourceEntity = SourceEntity(
    id = this.id,
    name = this.name
)
