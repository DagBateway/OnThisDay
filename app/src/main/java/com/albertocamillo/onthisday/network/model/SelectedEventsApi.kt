package com.albertocamillo.onthisday.network.model

import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Retrofit interface defining the API endpoint for retrieving "On This Day" selected events
 * from the Wikimedia feed service.
 *
 * The API returns a list of notable historical events for a specific date (month/day),
 * filtered by the desired language.
 *
 * Example API call:
 *   /feed/v1/wikipedia/en/onthisday/selected/7/21
 *
 * @return [SelectedEventsListApiModel] containing a list of [SelectedEventApiModel] entries.
 */
interface SelectedEventsApi {

    /**
     * Fetches selected historical events for the given language and date (month/day).
     *
     * @param language Language code (e.g. "en", "it") to localise the response.
     * @param month The numeric month (1–12).
     * @param day The numeric day of the month (1–31).
     */
    @GET("/feed/v1/wikipedia/{language}/onthisday/selected/{month}/{day}")
    suspend fun getSelectedEventsList(
        @Path("language") language: String,
        @Path("month") month: Int,
        @Path("day") day: Int
    ): SelectedEventsListApiModel
}
