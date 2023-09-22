package com.example.kakaoimglibrary.data

import android.util.Log
import com.example.kakaoimglibrary.common.utils.formatDateTime
import com.example.kakaoimglibrary.data.model.ImageSearchResponse
import com.example.kakaoimglibrary.model.SearchModel
import com.example.kakaoimglibrary.data.model.VideoSearchResponse

// Test Code
// 생성자로 RetrofitClient 넣기
// 생성하는 곳에서 데이터 소스를 전달해 줄 수 있게하고 Repository는 가져만 올 수 있게 리팩토링 예정 -> DI등..?
// DI를 공부하고 적용시키는건 너무 좋지만 아키텍쳐, 의존성 등등... 확실히 알고 가야할 개념을 먼저 마스터 하고 시도해보자.
class Repository(private val retrofit : RetrofitClient) {
    private val responseList: MutableList<SearchModel> = mutableListOf() // img, video 통합할 리스트 생성

    private var responseSearch = false
    private var responseVideo = false

    private suspend fun searchImage(query: String, sort: String, page: Int): ImageSearchResponse {
        return retrofit.api.searchImage(query = query, sort = sort, page = page, size = 20)
    }

    private suspend fun searchVideo(query: String, sort: String, page: Int): VideoSearchResponse {
        return retrofit.api.searchVideo(query = query, sort = sort, page = page, size = 20)
    }

    suspend fun responseData(query: String, sort: String, page: Int, isNew : Boolean): MutableList<SearchModel> {
        val getImageApi = searchImage(query, sort, page)
        val getVideoApi = searchVideo(query, sort, page)

        if (isNew){ // 새로운 검색일 경우 ListClear()
            responseList.clear()
        }

        getVideoApi.documents.videoToResponseModel().let {
            responseList.addAll(it.toMutableList())
        }
        getImageApi.documents.imageToResponseModel().let {
            responseList.addAll(it.toMutableList())
            Log.d("tt","video")
        }
        return responseList
    }

    private fun MutableList<ImageSearchResponse.Documents>.imageToResponseModel(): MutableList<SearchModel> {
        val list: MutableList<SearchModel> = mutableListOf()
        for (i in 0 until this.size) {
            list.add(
                i,
                SearchModel(
                    title = "[Image] ${this[i].displaySitename}",
                    dateTime = formatDateTime(this[i].datetime), // 날짜 포맷 함수 적용 후 매핑
                    thumbnailUri = this[i].imageUrl
                )
            )
        }
        return list
    }

    private fun MutableList<VideoSearchResponse.Documents>.videoToResponseModel(): MutableList<SearchModel> {
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