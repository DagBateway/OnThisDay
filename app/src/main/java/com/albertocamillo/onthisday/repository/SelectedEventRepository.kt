package com.albertocamillo.onthisday.repository

import com.albertocamillo.onthisday.database.AppDatabase
import com.albertocamillo.onthisday.database.asDomainModel
import com.albertocamillo.onthisday.domain.SelectedEvent
import com.albertocamillo.onthisday.network.model.SelectedEventsApi
import com.albertocamillo.onthisday.network.model.asDatabaseModel
import com.albertocamillo.onthisday.utils.generateUniqueID
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import timber.log.Timber
import java.time.LocalDate
import javax.inject.Inject

class SelectedEventRepository @Inject constructor(
    private val selectedEventApi: SelectedEventsApi,
    private val appDatabase: AppDatabase
) {
    private val current: LocalDate = LocalDate.now()
    private val month = current.month.value
    private val day = current.dayOfMonth
    val selectedEvents: Flow<List<SelectedEvent>?> =
        appDatabase.selectedEventsDao.getSelectedEvents(month, day).map {
            it?.asDomainModel()
        }

    suspend fun refreshSelectedEvents() {
        try {
            val selectedEventsList = selectedEventApi.getSelectedEventsList(month, day)
            appDatabase.selectedEventsDao.insertSelectedEvents(
                selectedEventsList.selectedEvents.asDatabaseModel(
                    month,
                    day
                )
            )
            for (event in selectedEventsList.selectedEvents) {
                appDatabase.selectedEventsDao.insertPages(
                    event.pages.asDatabaseModel(
                        generateUniqueID(event.year.toString(), event.text)
                    )
                )
            }

        } catch (e: Exception) {
            Timber.w(e)
        }
    }
}