package com.test.clean.data

import com.test.clean.commons.Output
import com.test.clean.data.remote.PostsRemoteDataSource
import com.test.clean.domain.PostsRepository
import com.test.clean.domain.model.Post
import javax.inject.Inject

class PostsRepositoryImpl @Inject constructor(
    private val postsRemoteDataSource: PostsRemoteDataSource
) : PostsRepository {

    override suspend fun getPosts(): Output<List<Post>> = postsRemoteDataSource.getPosts()
}
