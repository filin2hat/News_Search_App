package dev.filin2hat.news.data.mappers

import dev.filin2hat.news.data.models.Article
import dev.filin2hat.news.data.models.Source
import dev.filin2hat.newsapi.dto.ArticleDto
import dev.filin2hat.newsapi.dto.SourceDto

internal fun Article.toArticleDto(): ArticleDto =
    ArticleDto(
        sourceDto = this.source.toSourceDto(),
        author = this.author,
        title = this.title,
        description = this.description,
        url = this.url,
        urlToImage = this.urlToImage,
        publishedAt = this.publishedAt,
        content = this.content,
    )

private fun Source.toSourceDto(): SourceDto =
    SourceDto(
        id = this.id,
        name = this.name,
    )
