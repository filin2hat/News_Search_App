package dev.filin2hat.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import dev.filin2hat.database.dao.ArticleDao
import dev.filin2hat.database.entity.ArticleEntity
import dev.filin2hat.database.utils.Converters

class NewsDataBase internal constructor(private val dataBase: NewsRoomDataBase) {
    val articlesDao: ArticleDao
        get() = dataBase.articlesDao()
}

@Database(entities = [ArticleEntity::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
internal abstract class NewsRoomDataBase : RoomDatabase() {

    abstract fun articlesDao(): ArticleDao
}

fun NewsDataBase(applicationContext: Context): NewsDataBase {
    val newsRoomDataBase = Room.databaseBuilder(
        checkNotNull(applicationContext.applicationContext),
        NewsRoomDataBase::class.java,
        "news_database"
    ).build()

    return NewsDataBase(newsRoomDataBase)
}
