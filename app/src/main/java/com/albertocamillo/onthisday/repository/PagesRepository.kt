package com.albertocamillo.onthisday.repository

import com.albertocamillo.onthisday.database.AppDatabase
import com.albertocamillo.onthisday.database.asDomainModel
import com.albertocamillo.onthisday.domain.Page
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PagesRepository @Inject constructor(
    private val appDatabase: AppDatabase
) {

    fun getPages(selectedEventId: String): Flow<List<Page>?> =
        appDatabase.selectedEventsDao.getPagesBySelectedEventId(selectedEventId)
            .map { it?.asDomainModel() }
}