package com.example.kakaoimglibrary.bookmark

import android.os.Parcelable
import com.example.kakaoimglibrary.model.SearchModel
import kotlinx.parcelize.Parcelize

@Parcelize
data class BookmarkModel(
    val title : String,
    val dateTime : String,
    val thumbnailUri : String,
    val isBookmark : Boolean
) : Parcelable

fun BookmarkModel.toSearchModel(): SearchModel {
    return SearchModel(
        title = title,
        dateTime = dateTime,
        thumbnailUri = thumbnailUri,
        isBookmark = isBookmark
    )
}
