package com.example.kakaoimglibrary.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kakaoimglibrary.model.ImageSearchModel
import com.example.kakaoimglibrary.model.ResponseModel
import kotlinx.coroutines.launch

class SearchViewModel(
    private val repository: Repository
) : ViewModel() {

    private val _myPosts : MutableLiveData<List<ResponseModel>> = MutableLiveData()
    val myPosts : LiveData<List<ResponseModel>> get()= _myPosts

//    private var metadata : ImageSearchModel.MetaData? = null


    fun searchItems(searchText : String){
        viewModelScope.launch {
            val responseData = repository.responseData(searchText,"recency")

            _myPosts.value = responseData.toMutableList()
//            metadata = bodyImage?.metaData
        }
    }


//    fun searchItems(searchText : String){
//        viewModelScope.launch {
//            val responseImage = repository.searchImage(searchText,"recency")
//            val responseVideo = repository.searchVideo(searchText,"recency")
//
//            //DTO : 데이터 전송 오브젝트
//            val bodyImage = responseImage.body()
//            val bodyVideo = responseVideo.body()
//
//            _myPosts.value = bodyImage?.documents
//            metadata = bodyImage?.metaData
//        }
//    }
}