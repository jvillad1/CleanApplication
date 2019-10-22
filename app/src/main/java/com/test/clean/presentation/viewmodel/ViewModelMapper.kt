package com.test.clean.presentation.viewmodel

import com.test.clean.commons.BaseMapper
import com.test.clean.presentation.viewmodel.model.PostUI
import com.test.clean.domain.model.Post

class ViewModelMapper {

    object EntityToUI : BaseMapper<List<Post>, List<PostUI>> {
        override fun map(type: List<Post>?): List<PostUI> {
            return type?.map {
                PostUI(
                    id = it.id,
                    title = it.title,
                    body = it.body,
                    favorite = it.favorite
                )
            } ?: listOf()
        }
    }
}
