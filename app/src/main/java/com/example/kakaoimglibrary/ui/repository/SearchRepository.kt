package com.example.kakaoimglibrary.ui.repository

import com.example.kakaoimglibrary.ui.search.SearchModel

interface SearchRepository {
    suspend fun responseData(query: String, sort: String, page: Int, isNew : Boolean): MutableList<SearchModel>

}