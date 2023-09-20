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
            val response = repository.searchImage(searchText,"accuracy")
            val body = response.body()
            _myPosts.value = body?.documents
            metadata = body?.metaData
        }
    }
}


//class SearchViewModel(private val idGenerate: AtomicLong) : ViewModel() {
//
//    private val _list: MutableLiveData<List<SearchModel>> = MutableLiveData()
//    //읽기전용
//    val list: LiveData<List<SearchModel>> get() = _list
//
//    init {
//        _list.value = arrayListOf<SearchModel>().apply {
//            for (i in 0 until 10) {
//                add(
//                    SearchModel(
//                        idGenerate.getAndIncrement(),
//                        title = "test $i",
//                        datetime = "test:time: $i",
//                        url = "https://softychoo.github.io/assets/img/me.JPG",
//                        isBookmark = false
//                    )
//                )
//            }
//        }
//    }
//
//    fun modifySearchModel(searchModel: SearchModel, position: Int?){
//        val currentList = list.value.orEmpty().toMutableList()
//        val findPosition = position ?: currentList.findIndex(searchModel)
//        currentList[findPosition] = searchModel
//        _list.value = currentList
//    }
//
//    fun List<SearchModel>.findIndex(searchModel: SearchModel): Int {
//        return indexOfFirst { it.id == searchModel.id } // indexOfFirst : 가장 먼저 찾는 값 | 없으면 -1 return
//    }
//}