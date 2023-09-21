package com.example.kakaoimglibrary.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SearchModel(
    val title: String,
    val dateTime: String,
    val thumbnailUri: String,
    val isBookmark: Boolean = false
) : Parcelable
fun SearchModel.toBookmarkModel(): BookmarkModel {
    return BookmarkModel(
        title = title,
        dateTime = dateTime,
        thumbnailUri = thumbnailUri,
        isBookmark = isBookmark
    )
}


