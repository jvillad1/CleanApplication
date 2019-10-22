package com.test.clean.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.clean.commons.Output
import com.test.clean.domain.usecase.GetPostsUseCase
import com.test.clean.presentation.viewmodel.model.PostUI
import com.test.clean.domain.model.Post
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

class PostsViewModel @Inject constructor(
    private val getPostsUseCase: GetPostsUseCase
) : ViewModel() {

    private val postsLiveData: MutableLiveData<List<PostUI>> = MutableLiveData()
    private val favoritePostsLiveData: MutableLiveData<List<PostUI>> = MutableLiveData()
    private val emptyPostsLiveData: MutableLiveData<Boolean> = MutableLiveData()
    private val errorLiveData: MutableLiveData<Boolean> = MutableLiveData()
    private val loadingLiveData: MutableLiveData<Boolean> = MutableLiveData()

    val posts: LiveData<List<PostUI>>
        get() = postsLiveData

    val favoritePosts: LiveData<List<PostUI>>
        get() = favoritePostsLiveData

    val loading: LiveData<Boolean>
        get() = loadingLiveData

    fun loadPosts() {
        loadingLiveData.postValue(true)
        viewModelScope.launch {
            val output = getPostsUseCase.execute()
            if (output is Output.Success) {
                onGetPostsSuccess(output.data)
            } else {
                onGetPostsError()
            }
        }
    }

    fun clearPosts() {
        postsLiveData.postValue(emptyList())
    }

    fun addAsFavorite(postUI: PostUI) {
        val postFavorite = postsLiveData.value?.get(postUI.id)
        postFavorite?.favorite = true

        loadFavoritePosts()
    }

    fun loadFavoritePosts() {
        val favoritesList: List<PostUI>? = postsLiveData.value?.filter { post ->
            post.favorite
        }

        Timber.d("favoritesList size: ${favoritesList?.size}")
        if (favoritesList != null && favoritesList.isNotEmpty()) {
            favoritePostsLiveData.postValue(favoritesList)
        }
    }

    private fun onGetPostsSuccess(posts: List<Post>) {
        val postsUI = ViewModelMapper.EntityToUI.map(posts)

        if (postsUI.isEmpty()) {
            emptyPostsLiveData.postValue(true)
        } else {
            postsLiveData.postValue(postsUI)
        }

        loadingLiveData.postValue(false)
    }

    private fun onGetPostsError() {
        viewModelScope.launch {
            delay(300)
            loadingLiveData.postValue(false)
        }.invokeOnCompletion {
            errorLiveData.postValue(true)
        }
    }
}
