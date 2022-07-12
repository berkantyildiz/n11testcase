package com.n11.n11testcase.di

import android.app.Application
import androidx.room.Room
import com.n11.n11testcase.data.local.db.AppDatabase
import com.n11.n11testcase.data.local.db.dao.UserFavoriteDao
import com.n11.n11testcase.utils.const.databaseName
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object LocalModule {

    @Singleton
    @Provides
    fun provideAppDatabase(app: Application): AppDatabase {
        return Room.databaseBuilder(
            app,
            AppDatabase::class.java,
            databaseName
        ).build()
    }

    @Singleton
    @Provides
    fun provideUserFavoriteDao(appDatabase: AppDatabase): UserFavoriteDao {
        return appDatabase.userFavDao()
    }

}

