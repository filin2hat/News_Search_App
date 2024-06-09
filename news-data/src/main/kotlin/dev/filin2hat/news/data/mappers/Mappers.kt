package dev.filin2hat.news.data.mappers

import dev.filin2hat.database.entity.ArticleEntity
import dev.filin2hat.database.entity.SourceEntity
import dev.filin2hat.news.data.models.Article
import dev.filin2hat.news.data.models.Source

internal fun ArticleEntity.toArticle(): Article = Article(
    id = this.id,
    author = this.author,
    title = this.title,
    description = this.description,
    url = this.url,
    urlToImage = this.urlToImage,
    publishedAt = this.publishedAt,
    content = this.content,
    source = this.source.toSource()
)

internal fun Article.toArticleEntity(): ArticleEntity = ArticleEntity(
    id = this.id,
    author = this.author,
    title = this.title,
    description = this.description,
    url = this.url,
    urlToImage = this.urlToImage,
    publishedAt = this.publishedAt,
    content = this.content,
    source = this.source.toSourceEntity()
)

internal fun SourceEntity.toSource(): Source = Source(
    id = this.id,
    name = this.name
)

internal fun Source.toSourceEntity(): SourceEntity = SourceEntity(
    id = this.id,
    name = this.name
)
