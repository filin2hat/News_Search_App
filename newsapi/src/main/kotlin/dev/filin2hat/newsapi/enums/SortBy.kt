package dev.filin2hat.newsapi.enums

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class SortBy {
    @SerialName("popularity")
    Popularity,

    @SerialName("relevancy")
    Relevancy,

    @SerialName("publishedAt")
    PublishedAt
}
