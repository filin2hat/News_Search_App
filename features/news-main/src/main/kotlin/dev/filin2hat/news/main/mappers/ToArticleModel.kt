package dev.filin2hat.news.main.mappers

import dev.filin2hat.news.data.models.Article
import dev.filin2hat.news.data.models.Source
import dev.filin2hat.news.main.models.ArticleUIModel

internal fun Article.toArticleUIModel(): ArticleUIModel =
    ArticleUIModel(
        id = id,
        title = title,
        description = description,
        url = url,
        urlToImage = urlToImage,
        publishedAt = publishedAt,
        content = content,
        author = author,
        source = source.toSourceModel(),
    )

private fun Source.toSourceModel() =
    ArticleUIModel.Source(
        id = id,
        name = name,
    )
