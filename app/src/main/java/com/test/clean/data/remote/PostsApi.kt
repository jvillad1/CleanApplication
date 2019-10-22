package com.test.clean.data.remote

import com.test.clean.data.remote.model.PostResponse
import retrofit2.http.GET

interface PostsApi {

    @GET("posts")
    suspend fun getPosts(): List<PostResponse>
}
