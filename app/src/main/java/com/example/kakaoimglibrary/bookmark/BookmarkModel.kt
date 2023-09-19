package com.example.kakaoimglibrary.bookmark

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class BookmarkModel(
    val id: Long,
    val title: String,
    val datetime: String,
    val url: String,
    val isBookmark: Boolean
) : Parcelable