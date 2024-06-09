package dev.filin2hat.newsapi.enums

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class Language {
    @SerialName("ar")
    Ar,

    @SerialName("de")
    De,

    @SerialName("en")
    En,

    @SerialName("es")
    Es,

    @SerialName("fr")
    Fr,

    @SerialName("he")
    He,

    @SerialName("it")
    It,

    @SerialName("nl")
    Nl,

    @SerialName("no")
    No,

    @SerialName("pt")
    Pt,

    @SerialName("ru")
    Ru,

    @SerialName("sv")
    Sv,

    @SerialName("ud")
    Ud,

    @SerialName("zh")
    Zh
}
