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

/**
 * Dagger-Hilt module responsible for providing Room database-related dependencies.
 */
@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    /**
     * Provides the Room [AppDatabase] instance for the entire application.
     *
     * - Uses "SelectedEvents" as the database name.
     * - Applies fallback to destructive migration for simplicity (not suitable for production migrations).
     *
     * @param appContext The application [Context], automatically injected by Hilt.
     * @return A singleton instance of [AppDatabase].
     */
    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "SelectedEvents"
        ).fallbackToDestructiveMigration(false).build()
    }

    /**
     * Provides the [SelectedEventDao] used to interact with the events and pages tables.
     *
     * @param appDatabase The application's Room database instance.
     * @return A DAO for selected events and related pages.
     */
    @Provides
    fun provideChannelDao(appDatabase: AppDatabase): SelectedEventDao {
        return appDatabase.selectedEventsDao
    }
}
