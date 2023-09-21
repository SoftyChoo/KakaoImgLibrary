package com.example.kakaoimglibrary.search

import android.widget.Toast
import com.example.kakaoimglibrary.model.ImageSearchModel
import com.example.kakaoimglibrary.importAPI.RetrofitClient
import com.example.kakaoimglibrary.model.SearchModel
import com.example.kakaoimglibrary.model.VideoSearchModel
import retrofit2.Response

class Repository {
    suspend fun searchImage(query: String, sort: String): Response<ImageSearchModel> {
        return RetrofitClient.api.searchImage(query = query, sort = sort, page = 2, size = 10)
    }

    suspend fun searchVideo(query: String, sort: String): Response<VideoSearchModel> {
        return RetrofitClient.api.searchVideo(query = query, sort = sort, page = 2, size = 10)
    }

    var isImageSearchFinished = false
    var isVideoSearchFinished = false

    suspend fun responseData(query: String, sort: String): MutableList<SearchModel> {
        val responseList: MutableList<SearchModel> = mutableListOf() // img, video 통합할 리스트 생성
        val getImageApi = searchImage(query, sort)
        val getVideoApi = searchVideo(query, sort)

        isImageSearchFinished = true
        isVideoSearchFinished = true

        if (getImageApi.isSuccessful) { // retrofit 통신 성공했을 시
            getImageApi.body()?.documents?.imageToResponseModel()?.let {
                responseList.addAll(it.toMutableList())
                isImageSearchFinished = true
            }
        }

        if (getVideoApi.isSuccessful) {
            getVideoApi.body()?.documents?.videoToResponseModel()?.let {
                responseList.addAll(it.toMutableList())
                isVideoSearchFinished = true
            }
        }
        return if(isImageSearchFinished && isVideoSearchFinished){
            responseList.sortByDescending {it.dateTime}
            responseList
        } else{
            val emptyList : MutableList<SearchModel> = mutableListOf()
            emptyList
        }
    }


    private fun MutableList<ImageSearchModel.Documents>.imageToResponseModel(): MutableList<SearchModel> {
        val list: MutableList<SearchModel> = mutableListOf()
        for (i in 0 until this.size) {
            list.add(
                i,
                SearchModel(
                    title = "[Image] ${this[i].display_sitename}",
                    dateTime = this[i].datetime,
                    thumbnailUri = this[i].image_url
                )
            )
        }
        return list
    }

    private fun MutableList<VideoSearchModel.Documents>.videoToResponseModel(): MutableList<SearchModel> {
        val list: MutableList<SearchModel> = mutableListOf()
        for (i in 0 until this.size) {
            list.add(
                i,
                SearchModel(
                    title = "[Video] ${this[i].title}",
                    dateTime = this[i].datetime,
                    thumbnailUri = this[i].thumbnail
                )
            )
        }
        return list
    }

}