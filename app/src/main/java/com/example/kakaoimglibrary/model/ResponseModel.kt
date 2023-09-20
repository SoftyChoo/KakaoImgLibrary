package com.example.kakaoimglibrary.model

import android.os.Parcelable
import com.example.kakaoimglibrary.bookmark.BookmarkModel
import kotlinx.parcelize.Parcelize

@Parcelize
data class ResponseModel(
    val title: String,
    val dateTime: String,
    val thumbnailUri: String,
    val isBookmark: Boolean = false
) : Parcelable

fun ResponseModel.toBookmarkModel(): BookmarkModel {
    return BookmarkModel(
        title = title,
        dateTime = dateTime,
        thumbnailUri = thumbnailUri,
        isBookmark = isBookmark
    )
}
