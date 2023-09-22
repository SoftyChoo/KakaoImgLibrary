package com.example.kakaoimglibrary.viewmodel.search

import android.app.Activity
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kakaoimglibrary.model.SearchModel
import com.example.kakaoimglibrary.data.Repository
import com.example.kakaoimglibrary.main.MainActivity
import com.example.kakaoimglibrary.ui.search.SearchFragment
import kotlinx.coroutines.launch

class SearchViewModel(
    private val repository: Repository
) : ViewModel() {

    private val _list : MutableLiveData<List<SearchModel>> = MutableLiveData()
    val list : LiveData<List<SearchModel>> get()= _list

    private var searchPage = 1
    private var searchNextPageDataText = ""

    // 매핑된 List(Image, Video)를 가져옴
    fun searchItems(searchText : String){
        searchPage = 1
        searchNextPageDataText = searchText
        viewModelScope.launch {
            kotlin.runCatching { // 완료
                val responseData = repository.responseData(searchText,"recency", searchPage, true)
                responseData.sortByDescending { it.dateTime } // 날짜 순 정렬
                _list.value = responseData.toMutableList()
            }.onFailure {
                // 실패 시 동작 처리
            }
        }
    }

    // 다음 페이지 가져오기
    fun searchNextPageData() {
        searchPage++
        viewModelScope.launch {
            val responseData = repository.responseData(searchNextPageDataText,"recency", searchPage,false)
            _list.value = responseData.toMutableList()
        }
    }

    // 리스트 수정
    fun modifyList(item: SearchModel, position: Int?) {
        val currentList = list.value?.toMutableList()
        val findPosition = position ?: findIndex(item)
        if (findPosition != null && findPosition != -1) // 현재 List에 BookmarkItem이 없을 때 예외처리
        {
            currentList?.set(findPosition, item)
        }
        _list.value = currentList
    }

    // 일치하는 데이터 찾기
    private fun findIndex(searchModel: SearchModel) : Int?{
        val currentList = list.value?.toMutableList()
        val findByURL = currentList?.find {
            it.thumbnailUri == searchModel.thumbnailUri
        }
        return currentList?.indexOf(findByURL)
    }
}