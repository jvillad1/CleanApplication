package com.test.clean.di

import android.app.Application
import com.test.clean.CleanApp
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object ApplicationModule {

    @Provides
    @JvmStatic
    @Singleton
    internal fun provideApplication(application: CleanApp): Application = application
}
