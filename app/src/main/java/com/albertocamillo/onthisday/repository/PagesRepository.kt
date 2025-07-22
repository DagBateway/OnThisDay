package com.albertocamillo.onthisday.repository

import com.albertocamillo.onthisday.database.AppDatabase
import com.albertocamillo.onthisday.database.asDomainModel
import com.albertocamillo.onthisday.domain.Page
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * Repository for accessing detailed Wikipedia page data linked to a selected historical event.
 *
 * Retrieves related page information from the local Room database.
 */
class PagesRepository @Inject constructor(
    private val appDatabase: AppDatabase
) {

    /**
     * Returns a Flow of a list of [Page]s associated with a selected event ID.
     *
     * This is used in the Details screen to display related Wikipedia pages.
     *
     * @param selectedEventId the unique ID generated from the event's year and text.
     */
    fun getPages(selectedEventId: String): Flow<List<Page>?> =
        appDatabase.selectedEventsDao.getPagesBySelectedEventId(selectedEventId)
            .map { it?.asDomainModel() }
}
