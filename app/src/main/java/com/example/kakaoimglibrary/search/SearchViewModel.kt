package com.example.kakaoimglibrary.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kakaoimglibrary.model.SearchModel
import kotlinx.coroutines.launch

class SearchViewModel(
    private val repository: Repository
) : ViewModel() {

    private val _list : MutableLiveData<List<SearchModel>> = MutableLiveData()
    val list : LiveData<List<SearchModel>> get()= _list

//    private var metadata : ImageSearchModel.MetaData? = null


    fun searchItems(searchText : String){
        viewModelScope.launch {
            val responseData = repository.responseData(searchText,"recency")

            _list.value = responseData.toMutableList()
//            metadata = bodyImage?.metaData
        }
    }

    fun modifyList(item: SearchModel, position: Int?) {
        val currentList = list.value?.toMutableList()

        val findPosition = position ?: findIndex(item)
        findPosition?.let { currentList?.set(it, item) }
        _list.value = currentList
    }

    fun findIndex(searchModel: SearchModel) : Int?{
        val currentList = list.value?.toMutableList()
        val findByURL = currentList?.find {
            it.thumbnailUri == searchModel.thumbnailUri
        }
        return currentList?.indexOf(findByURL)
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