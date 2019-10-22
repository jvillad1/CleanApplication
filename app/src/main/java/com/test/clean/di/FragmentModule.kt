package com.test.clean.di

import com.test.clean.presentation.FavoritesFragment
import com.test.clean.presentation.PostsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract fun bindPostsFragment(): PostsFragment

    @ContributesAndroidInjector
    abstract fun bindFavoritesFragment(): FavoritesFragment
}
