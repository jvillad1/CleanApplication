package com.test.clean

import com.test.clean.di.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import timber.log.Timber

class CleanApp : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
        DaggerApplicationComponent.builder().application(this).build()

    override fun onCreate() {
        super.onCreate()

        // Timber
        Timber.plant(Timber.DebugTree())
    }
}
