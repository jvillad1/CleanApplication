package com.test.clean.domain.usecase

import com.test.clean.commons.Output
import com.test.clean.domain.PostsRepository
import com.test.clean.domain.model.Post
import java.io.IOException
import javax.inject.Inject


class GetPostsUseCase @Inject constructor(private val postsRepository: PostsRepository) {

    suspend fun execute(): Output<List<Post>> {
        var postsOutput: Output<List<Post>> = Output.Success((listOf()))

        postsRepository.getPosts()?.let { output ->
            postsOutput = if (output is Output.Success) {
                Output.Success(output.data)
            } else {
                Output.Error(IOException())
            }
        }

        return postsOutput
    }
}
