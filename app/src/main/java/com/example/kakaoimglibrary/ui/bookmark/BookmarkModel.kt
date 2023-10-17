package com.example.kakaoimglibrary.ui.bookmark

import android.os.Parcelable
import com.example.kakaoimglibrary.ui.search.SearchModel
import kotlinx.parcelize.Parcelize

@Parcelize
data class BookmarkModel(
    val title: String,
    val dateTime: String,
    val thumbnailUri: String,
    val isBookmark: Boolean
) : Parcelable

fun BookmarkModel.toSearchModel(): SearchModel {
    return SearchModel(
        title = title,
        dateTime = dateTime,
        thumbnailUri = thumbnailUri,
        isBookmark = isBookmark
    )
}
