package com.n11.n11testcase.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.n11.n11testcase.data.local.db.dao.UserFavoriteDao
import com.n11.n11testcase.data.local.db.entity.UserFavoriteEntity

@Database(
    entities = [UserFavoriteEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userFavDao(): UserFavoriteDao

}
