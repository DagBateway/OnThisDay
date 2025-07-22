package com.albertocamillo.onthisday.di

import com.albertocamillo.onthisday.BuildConfig
import com.albertocamillo.onthisday.network.model.SelectedEventsApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

/**
 * Dagger-Hilt module that provides network-related dependencies such as OkHttpClient,
 * Retrofit, and the API service used to retrieve historical events.
 */
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    /**
     * Provides a configured OkHttpClient instance.
     *
     * - In debug builds, a [HttpLoggingInterceptor] is added to log full request/response data.
     * - In release builds, logging is omitted to improve performance and security.
     *
     * @return An instance of [OkHttpClient].
     */
    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient = if (BuildConfig.DEBUG) {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    } else {
        OkHttpClient.Builder().build()
    }

    /**
     * Provides a configured Retrofit instance using Moshi for JSON conversion.
     *
     * @param okHttpClient The [OkHttpClient] used for executing HTTP requests.
     * @return An instance of [Retrofit] pointing to the Wikimedia API.
     */
    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl("https://api.wikimedia.org/")
        .addConverterFactory(MoshiConverterFactory.create())
        .client(okHttpClient)
        .build()

    /**
     * Provides the implementation of the [SelectedEventsApi] interface.
     *
     * This API interface is used to fetch "On This Day" selected events data from Wikimedia.
     *
     * @param retrofit The [Retrofit] instance used to create the API service.
     * @return A concrete implementation of [SelectedEventsApi].
     */
    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): SelectedEventsApi =
        retrofit.create(SelectedEventsApi::class.java)
}
