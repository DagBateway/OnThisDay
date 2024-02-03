package com.albertocamillo.onthisday.di

import android.content.Context
import androidx.room.Room
import com.albertocamillo.onthisday.database.AppDatabase
import com.albertocamillo.onthisday.database.SelectedEventDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {
    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "SelectedEvents"
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    fun provideChannelDao(appDatabase: AppDatabase): SelectedEventDao {
        return appDatabase.selectedEventsDao
    }
}