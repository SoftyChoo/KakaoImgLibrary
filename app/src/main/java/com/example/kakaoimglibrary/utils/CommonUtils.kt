package com.example.kakaoimglibrary.utils

import android.content.Context
import com.example.kakaoimglibrary.ui.bookmark.BookmarkModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.text.SimpleDateFormat
import java.util.Locale

// 날짜 형식 변경 함수
fun formatDateTime(dateTime: String): String {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ", Locale.getDefault())
    val outputFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
    return inputFormat.parse(dateTime).let { outputFormat.format(it) }
}

// 리스트의 형태로 데이터 저장
fun saveBookmarkData(context: Context, key: String, values: List<BookmarkModel>) {
    val gson = Gson()
    val json = gson.toJson(values)
    val prefs = context.getSharedPreferences("SETTINGS", Context.MODE_PRIVATE)
    val editor = prefs?.edit()
    editor?.putString(key, json)
    editor?.apply()
}

// 데이터 로드
fun loadBookmarkData(context: Context, key: String): List<BookmarkModel> {
    val prefs = context.getSharedPreferences("SETTINGS", Context.MODE_PRIVATE)
    val json = prefs?.getString(key, null)

    return if (json != null) {
        val gson = Gson()
        val storedData: List<BookmarkModel> =
            gson.fromJson(json, object : TypeToken<List<BookmarkModel>>() {}.type)
        storedData
    } else {
        emptyList() // null일 때 빈 리스트 반환
    }
}