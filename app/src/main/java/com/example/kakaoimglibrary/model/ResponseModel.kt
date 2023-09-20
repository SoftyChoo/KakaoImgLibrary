package com.example.kakaoimglibrary.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ResponseModel(
    val title : String,
    val dateTime : String,
    val thumbnailUri : String,
): Parcelable
