package com.test.clean.di

import com.test.clean.BuildConfig
import com.test.clean.commons.RemoteSettings.POSTS_API_URL
import com.test.clean.data.remote.PostsApi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
object NetworkModule {

    @Provides
    @JvmStatic
    internal fun providesLoggingInterceptor(): HttpLoggingInterceptor? =
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
        } else null

    @Provides
    @JvmStatic
    internal fun providesOkHttpClientBuilder(
        loggingInterceptor: HttpLoggingInterceptor?
    ): OkHttpClient.Builder =
        OkHttpClient.Builder().apply {
            loggingInterceptor?.also {
                addInterceptor(it)
            }
        }

    @Provides
    @JvmStatic
    internal fun providesPostsOkHttpClient(
        builder: OkHttpClient.Builder
    ): OkHttpClient = builder.build()

    @Provides
    @Singleton
    @JvmStatic
    internal fun providesPostsRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(POSTS_API_URL)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

    @Provides
    @JvmStatic
    internal fun providesPostsApi(retrofit: Retrofit): PostsApi =
        retrofit.create(PostsApi::class.java)
}
