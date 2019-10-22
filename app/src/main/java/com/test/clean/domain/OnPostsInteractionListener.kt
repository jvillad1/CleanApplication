package com.test.clean.domain

import com.test.clean.presentation.viewmodel.model.PostUI

interface OnPostsInteractionListener {

    fun onPostsClick(postUI: PostUI)
}
