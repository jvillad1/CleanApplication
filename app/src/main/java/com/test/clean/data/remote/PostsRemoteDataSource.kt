package com.test.clean.data.remote

import com.test.clean.commons.Output
import com.test.clean.domain.model.Post
import java.io.IOException
import javax.inject.Inject

class PostsRemoteDataSource @Inject constructor(
    private val postsApi: PostsApi
) {

    suspend fun getPosts(): Output<List<Post>> =
        try {
            val posts = RemoteMapper.RemoteToEntity.map(postsApi.getPosts())
            Output.Success(posts)
        } catch (e: Throwable){
            Output.Error(IOException("Exception ${e.message}"))
        }
}
