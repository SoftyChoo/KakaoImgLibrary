package com.example.kakaoimglibrary.search

import com.example.kakaoimglibrary.model.ImageSearchModel
import com.example.kakaoimglibrary.importAPI.RetrofitClient
import com.example.kakaoimglibrary.model.VideoSearchModel
import retrofit2.Response

class Repository {
    suspend fun searchImage(query : String, sort : String) : Response<ImageSearchModel> {
        return RetrofitClient.api.searchImage(query = query, sort = sort, page = 2, size = 10)
    }
    suspend fun searchVideo(query : String, sort : String) : Response<VideoSearchModel> {
        return RetrofitClient.api.searchVideo(query = query, sort = sort, page = 2, size = 10)
    }
}