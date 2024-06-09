package dev.filin2hat.news.data.models

import java.util.Date

class Article(
    val id: Long,
    val source: Source,
    val author: String,
    val title: String,
    val description: String,
    val url: String,
    val urlToImage: String,
    val publishedAt: Date,
    val content: String,
)

class Source(
    val id: String,
    val name: String,
)
