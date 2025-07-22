package com.albertocamillo.onthisday.repository

import com.albertocamillo.onthisday.database.AppDatabase
import com.albertocamillo.onthisday.database.asDomainModel
import com.albertocamillo.onthisday.domain.SelectedEvent
import com.albertocamillo.onthisday.domain.getLanguage
import com.albertocamillo.onthisday.network.model.SelectedEventsApi
import com.albertocamillo.onthisday.network.model.asDatabaseModel
import com.albertocamillo.onthisday.utils.generateUniqueID
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import timber.log.Timber
import java.time.LocalDate
import java.util.Locale
import javax.inject.Inject

/**
 * Repository responsible for retrieving and caching "On This Day" events.
 *
 * - Retrieves data from the API (based on device language and current date).
 * - Caches results in a local Room database.
 * - Exposes a reactive [Flow] of selected events, fetched from the local database.
 */
class SelectedEventRepository @Inject constructor(
    private val selectedEventApi: SelectedEventsApi,
    private val appDatabase: AppDatabase
) {
    private val currentLanguage: String = Locale.getDefault().language
    private val currentDate: LocalDate = LocalDate.now()
    private val month = currentDate.month.value
    private val day = currentDate.dayOfMonth

    /**
     * A reactive flow of selected events for today's date, pulled from local Room DB.
     */
    val selectedEvents: Flow<List<SelectedEvent>?> =
        appDatabase.selectedEventsDao.getSelectedEvents(month, day).map {
            it?.asDomainModel()
        }

    /**
     * Refreshes the selected events by fetching from the remote API and storing in the local DB.
     * Also stores associated Wikipedia pages for each event using a generated unique ID.
     */
    suspend fun refreshSelectedEvents() {
        try {
            val selectedEventsList = selectedEventApi.getSelectedEventsList(
                getLanguage(currentLanguage),
                month,
                day
            )

            // Save events to local DB
            appDatabase.selectedEventsDao.insertSelectedEvents(
                selectedEventsList.selectedEvents.asDatabaseModel(month, day)
            )

            // Save related Wikipedia pages to DB, linked via a generated ID
            for (event in selectedEventsList.selectedEvents) {
                appDatabase.selectedEventsDao.insertPages(
                    event.pages.asDatabaseModel(
                        generateUniqueID(event.year.toString(), event.text)
                    )
                )
            }

        } catch (e: Exception) {
            Timber.w(e) // Logs exception using Timber for debugging
        }
    }
}
