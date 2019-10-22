package com.test.clean.presentation.viewmodel.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PostUI (
    val id: Int,
    val title: String,
    val body: String,
    var favorite: Boolean = false
): Parcelable
