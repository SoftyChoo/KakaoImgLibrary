package com.example.kakaoimglibrary.search

import com.example.kakaoimglibrary.model.ImageSearchModel
import com.example.kakaoimglibrary.importAPI.RetrofitClient
import com.example.kakaoimglibrary.model.ResponseModel
import com.example.kakaoimglibrary.model.VideoSearchModel
import retrofit2.Response

class Repository {
    suspend fun searchImage(query: String, sort: String): Response<ImageSearchModel> {
        return RetrofitClient.api.searchImage(query = query, sort = sort, page = 2, size = 10)
    }

    suspend fun searchVideo(query: String, sort: String): Response<VideoSearchModel> {
        return RetrofitClient.api.searchVideo(query = query, sort = sort, page = 2, size = 10)
    }

    suspend fun responseData(query: String, sort: String): MutableList<ResponseModel> {
        val responseList: MutableList<ResponseModel> = mutableListOf() // img, video 통합할 리스트 생성
        val getImageApi = searchImage(query, sort)
        val getVideoApi = searchVideo(query, sort)

        if (getImageApi.isSuccessful) {
            getImageApi.body()?.documents?.imageToResponseModel()?.let {
                responseList.addAll(it.toMutableList())
            }
        }

        if (getVideoApi.isSuccessful) {
            getVideoApi.body()?.documents?.videoToResponseModel()?.let {
                responseList.addAll(it.toMutableList())
            }
        }
        return responseList
    }

    private fun MutableList<ImageSearchModel.Documents>.imageToResponseModel(): MutableList<ResponseModel> {
        val list: MutableList<ResponseModel> = mutableListOf()
        for (i in 0 until this.size) {
            list.add(
                i,
                ResponseModel(
                    title = this[i].display_sitename,
                    dateTime = this[i].datetime,
                    thumbnailUri = this[i].image_url
                )
            )
        }
        return list
    }

    private fun MutableList<VideoSearchModel.Documents>.videoToResponseModel(): MutableList<ResponseModel> {
        val list: MutableList<ResponseModel> = mutableListOf()
        for (i in 0 until this.size) {
            list.add(
                i,
                ResponseModel(
                    title = this[i].title,
                    dateTime = this[i].datetime,
                    thumbnailUri = this[i].thumbnail
                )
            )
        }
        return list
    }

}