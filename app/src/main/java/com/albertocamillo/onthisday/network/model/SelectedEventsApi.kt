package com.albertocamillo.onthisday.network.model

import retrofit2.http.GET
import retrofit2.http.Path

interface SelectedEventsApi {

    @GET("/feed/v1/wikipedia/en/onthisday/selected/{month}/{day}")
    suspend fun getSelectedEventsList(
        @Path("month") month: Int,
        @Path("day") day: Int
    ): SelectedEventsListApiModel

}