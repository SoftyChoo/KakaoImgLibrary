package com.example.kakaoimglibrary.importAPI

import com.example.kakaoimglibrary.importAPI.Constants.Companion.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient { // Client
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val api : com.example.kakaoimglibrary.importAPI.Retrofit by lazy {
        retrofit.create(com.example.kakaoimglibrary.importAPI.Retrofit::class.java
        )
    }
}

