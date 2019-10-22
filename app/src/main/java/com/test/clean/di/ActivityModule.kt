package com.test.clean.di

import com.test.clean.presentation.PostsActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector
    abstract fun bindPostsActivity(): PostsActivity
}
