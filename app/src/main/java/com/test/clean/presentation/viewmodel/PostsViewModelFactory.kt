package com.test.clean.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.test.clean.domain.usecase.GetPostsUseCase
import javax.inject.Inject

class PostsViewModelFactory @Inject constructor(
    private val getPostsUseCase: GetPostsUseCase
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PostsViewModel::class.java)) {
            return PostsViewModel(getPostsUseCase) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
