package com.example.kakaoimglibrary.bookmark

import android.os.Parcelable
import com.example.kakaoimglibrary.search.SearchModel
import kotlinx.parcelize.Parcelize

@Parcelize
data class BookmarkModel(
    val id: Long,
    val title: String,
    val datetime: String,
    val url: String,
    val isBookmark: Boolean
) : Parcelable
fun BookmarkModel.toSearchModel() : SearchModel{
    return SearchModel(
        id = id ,
        title = title,
        datetime = datetime,
        url = url,
        isBookmark = isBookmark
    )
}