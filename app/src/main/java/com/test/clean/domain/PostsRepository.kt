package com.test.clean.domain

import com.test.clean.commons.Output
import com.test.clean.domain.model.Post

interface PostsRepository {

    suspend fun getPosts(): Output<List<Post>>?
}
