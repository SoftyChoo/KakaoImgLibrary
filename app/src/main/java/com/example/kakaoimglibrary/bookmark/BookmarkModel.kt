package com.example.kakaoimglibrary.bookmark

import android.os.Parcelable
import com.example.kakaoimglibrary.search.SearchModel
import kotlinx.parcelize.Parcelize

@Parcelize
data class BookmarkModel(
    val title : String,
    val dateTime : String,
    val thumbnailUri : String,
    val isBookmark : Boolean
) : Parcelable
