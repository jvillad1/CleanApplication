package com.test.clean.di

import com.test.clean.data.PostsRepositoryImpl
import com.test.clean.data.remote.PostsRemoteDataSource
import com.test.clean.domain.PostsRepository
import dagger.Module
import dagger.Provides

@Module(includes = [NetworkModule::class])
object DataModule {

    @Provides
    @JvmStatic
    internal fun providesRepository(postsRemoteDataSource: PostsRemoteDataSource): PostsRepository =
        PostsRepositoryImpl(postsRemoteDataSource)
}
