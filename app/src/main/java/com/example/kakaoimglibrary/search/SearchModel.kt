package com.example.kakaoimglibrary.search

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SearchModel(
    val id: Long,
    val title: String,
    val datetime: String,
    val url: String,
    val isBookmark: Boolean
) : Parcelable
