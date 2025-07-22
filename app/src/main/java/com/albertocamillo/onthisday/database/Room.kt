package com.albertocamillo.onthisday.database

import androidx.room.Dao
import androidx.room.Database
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.RoomDatabase
import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object (DAO) for accessing selected historical events and their related pages.
 * Defines all SQL queries and operations used by the Room database.
 */
@Dao
interface SelectedEventDao {

    /**
     * Retrieves all selected historical events for a specific day and month.
     *
     * @param month The numeric month (e.g., 1 for January).
     * @param day The numeric day of the month.
     * @return A Flow emitting a list of [SelectedEventEntity]s or null if not found.
     */
    @Query("SELECT * FROM SelectedEventEntity WHERE month = :month AND day = :day")
    fun getSelectedEvents(month: Int, day: Int): Flow<List<SelectedEventEntity>?>

    /**
     * Inserts a list of selected historical events into the database.
     * Replaces existing entries if there's a conflict on the primary key.
     *
     * @param selectedEvents List of events to insert.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSelectedEvents(selectedEvents: List<SelectedEventEntity>)

    /**
     * Retrieves all related Wikipedia pages for all stored events.
     *
     * @return A Flow emitting a list of [PageEntity]s or null.
     */
    @Query("SELECT * FROM PageEntity")
    fun getPages(): Flow<List<PageEntity>?>

    /**
     * Inserts a list of page entities into the database.
     * Replaces existing entries on conflict.
     *
     * @param pages List of related Wikipedia pages.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPages(pages: List<PageEntity>)

    /**
     * Retrieves all related pages for a specific selected event.
     *
     * @param selectedEventId The unique ID of the selected event.
     * @return A Flow emitting a list of [PageEntity]s or null.
     */
    @Query("SELECT * FROM PageEntity WHERE selectedEventId = :selectedEventId")
    fun getPagesBySelectedEventId(selectedEventId: String): Flow<List<PageEntity>?>
}

/**
 * Main Room database class for the app.
 * Holds the database and provides DAOs for querying data.
 *
 * @property selectedEventsDao DAO to access selected events and related pages.
 */
@Database(entities = [SelectedEventEntity::class, PageEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract val selectedEventsDao: SelectedEventDao
}
