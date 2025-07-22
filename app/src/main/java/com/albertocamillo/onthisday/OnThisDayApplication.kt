package com.albertocamillo.onthisday

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

/**
 * Application class for the OnThisDay app.
 *
 * Responsibilities:
 * - Sets up the global application context.
 * - Enables Dagger Hilt for dependency injection by annotating with [@HiltAndroidApp].
 * - Initialises Timber logging for debug builds.
 *
 * Timber is a logging utility used to make logging more consistent and manageable.
 * In debug mode, it plants a [Timber.DebugTree] to print logs to Logcat.
 */
@HiltAndroidApp
class OnThisDayApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}
