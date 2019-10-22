package com.test.clean.data.remote

import com.test.clean.commons.BaseMapper
import com.test.clean.data.remote.model.PostResponse
import com.test.clean.domain.model.Post

class RemoteMapper {

    object RemoteToEntity : BaseMapper<List<PostResponse>, List<Post>> {
        override fun map(type: List<PostResponse>?): List<Post> {
            return type?.map {
                Post(
                    userId = it.userId,
                    id = it.id,
                    title = it.title,
                    body = it.body
                )
            } ?: listOf()
        }
    }
}
