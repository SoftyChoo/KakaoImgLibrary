package com.example.kakaoimglibrary.ui.search

import android.os.Parcelable
import com.example.kakaoimglibrary.ui.bookmark.BookmarkModel
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


