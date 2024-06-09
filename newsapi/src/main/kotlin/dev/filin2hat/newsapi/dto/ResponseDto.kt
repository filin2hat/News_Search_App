package dev.filin2hat.newsapi.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseDto<E>(

    @SerialName("status")
    val status: String,

    @SerialName("totalResults")
    val totalResults: Int,

    @SerialName("articles")
    val articles: List<E>
)
