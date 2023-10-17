package com.example.kakaoimglibrary.retrofit

import com.example.kakaoimglibrary.retrofit.Constants.Companion.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient { // Client
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val api: com.example.kakaoimglibrary.data.remote.SearchRemoteDataSource by lazy {
        retrofit.create(
            com.example.kakaoimglibrary.data.remote.SearchRemoteDataSource::class.java
        )
    }
}

