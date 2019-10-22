package com.test.clean

import android.app.Application
import timber.log.Timber

class CleanApp : Application() {

    override fun onCreate() {
        super.onCreate()

        // Timber
        Timber.plant(Timber.DebugTree())
    }
}
