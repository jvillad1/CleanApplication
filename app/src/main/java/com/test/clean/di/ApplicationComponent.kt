package com.test.clean.di

import android.app.Application
import com.test.clean.CleanApp
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        ApplicationModule::class,
        DataModule::class,
        ActivityModule::class,
        FragmentModule::class
    ]
)
interface ApplicationComponent : AndroidInjector<CleanApp> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): ApplicationComponent
    }
}
