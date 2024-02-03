package com.albertocamillo.onthisday.database

import androidx.room.Dao
import androidx.room.Database
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.RoomDatabase
import kotlinx.coroutines.flow.Flow

@Dao
interface SelectedEventDao {
    @Query("select * from SelectedEventEntity")
    fun getSelectedEvents(): Flow<List<SelectedEventEntity>?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSelectedEvents(selectedEvents: List<SelectedEventEntity>)

    @Query("select * from PageEntity")
    fun getPages(): Flow<List<PageEntity>?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPages(pages: List<PageEntity>)
}

@Database(entities = [SelectedEventEntity::class, PageEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract val selectedEventsDao: SelectedEventDao
}