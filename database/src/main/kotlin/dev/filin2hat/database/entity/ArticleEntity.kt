package dev.filin2hat.database.entity

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "articles")
class ArticleEntity(
    @PrimaryKey(autoGenerate = true) val id: Long?,
    @Embedded(prefix = "source_") val source: SourceEntity,
    @ColumnInfo(name = "author") val author: String,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "url") val url: String,
    @ColumnInfo(name = "urlToImage") val urlToImage: String,
    @ColumnInfo(name = "publishedAt") val publishedAt: Date,
    @ColumnInfo(name = "content") val content: String,
)

data class SourceEntity(
    @ColumnInfo(name = "id") val id: String,
    @ColumnInfo(name = "name") val name: String,
)
