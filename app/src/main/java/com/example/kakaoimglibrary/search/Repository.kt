package com.example.kakaoimglibrary.search

import com.example.kakaoimglibrary.importAPI.ImageSearchModel
import com.example.kakaoimglibrary.importAPI.RetrofitClient
import retrofit2.Response

class Repository {
    suspend fun searchImage(query : String, sort : String) : Response<ImageSearchModel> {
        return RetrofitClient.api.searchImage(query = query, sort = sort, page = 2, size = 10)
    }
}