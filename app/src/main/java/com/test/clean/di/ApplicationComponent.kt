package com.test.clean.di

import com.test.clean.CleanApp
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        ApplicationModule::class,
        ActivityModule::class,
        FragmentModule::class
    ]
)
interface ApplicationComponent : AndroidInjector<CleanApp> {

    @Component.Factory
    abstract class Builder : AndroidInjector.Builder<CleanApp>()
}
