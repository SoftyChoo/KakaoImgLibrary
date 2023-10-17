package com.example.kakaoimglibrary.data.remote

import com.example.kakaoimglibrary.retrofit.Constants
import com.example.kakaoimglibrary.data.model.ImageSearchResponse
import com.example.kakaoimglibrary.data.model.VideoSearchResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface SearchRemoteDataSource {
    @GET("v2/search/image")
    suspend fun searchImage(
        @Header("Authorization") apiKey: String = Constants.AUTH_HEADER,
        @Query("query") query: String,
        @Query("sort") sort: String,
        @Query("page") page: Int,
        @Query("size") size: Int
    ): ImageSearchResponse

    @GET("v2/search/vclip")
    suspend fun searchVideo(
        @Header("Authorization") apiKey: String = Constants.AUTH_HEADER,
        @Query("query") query: String,
        @Query("sort") sort: String,
        @Query("page") page: Int,
        @Query("size") size: Int
    ): VideoSearchResponse
}