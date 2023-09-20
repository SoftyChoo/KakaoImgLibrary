package com.example.kakaoimglibrary.search

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kakaoimglibrary.importAPI.ImageSearchModel
import kotlinx.coroutines.launch
import retrofit2.Response

class SearchViewModel(
    private val repository: Repository
) : ViewModel() {

    private val _myPosts : MutableLiveData<List<ImageSearchModel.Documents>> = MutableLiveData()
    val myPosts : LiveData<List<ImageSearchModel.Documents>> get()= _myPosts

    private var metadata : ImageSearchModel.MetaData? = null

    fun searchImage(searchText : String){
        viewModelScope.launch {
            val response = repository.searchImage(searchText,"recency")
            val body = response.body()
            _myPosts.value = body?.documents
            metadata = body?.metaData
        }
    }
}