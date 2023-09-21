package com.example.kakaoimglibrary.data

import com.example.kakaoimglibrary.utils.formatDateTime
import com.example.kakaoimglibrary.data.model.ImageSearchModel
import com.example.kakaoimglibrary.model.SearchModel
import com.example.kakaoimglibrary.data.model.VideoSearchModel
import retrofit2.Response

class Repository {
    val responseList: MutableList<SearchModel> = mutableListOf() // img, video 통합할 리스트 생성

    suspend fun searchImage(query: String, sort: String, page: Int): Response<ImageSearchModel> {
        return RetrofitClient.api.searchImage(query = query, sort = sort, page = page, size = 20)
    }

    suspend fun searchVideo(query: String, sort: String, page: Int): Response<VideoSearchModel> {
        return RetrofitClient.api.searchVideo(query = query, sort = sort, page = page, size = 20)
    }

    suspend fun responseData(query: String, sort: String, page: Int, isNew : Boolean): MutableList<SearchModel> {
        val getImageApi = searchImage(query, sort, page)
        val getVideoApi = searchVideo(query, sort, page)

        if (isNew){ // 새로운 검색일 경우 ListClear
            responseList.clear()
        }

        if (getImageApi.isSuccessful && getVideoApi.isSuccessful) { // retrofit 통신 둘 다 받아왔을 경우

            getVideoApi.body()?.documents?.videoToResponseModel()?.let {
                responseList.addAll(it.toMutableList())
            }

            getImageApi.body()?.documents?.imageToResponseModel()?.let {
                responseList.addAll(it.toMutableList())
            }

            responseList.sortByDescending { it.dateTime } // 날짜 순 정렬
        }
        return responseList
    }

    private fun MutableList<ImageSearchModel.Documents>.imageToResponseModel(): MutableList<SearchModel> {
        val list: MutableList<SearchModel> = mutableListOf()
        for (i in 0 until this.size) {
            list.add(
                i,
                SearchModel(
                    title = "[Image] ${this[i].display_sitename}",
                    dateTime = formatDateTime(this[i].datetime), // 날짜 포맷 함수 적용 후 매핑
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
                    dateTime = formatDateTime(this[i].datetime), // 날짜 포맷 함수 적용 후 매핑
                    thumbnailUri = this[i].thumbnail
                )
            )
        }
        return list
    }
}