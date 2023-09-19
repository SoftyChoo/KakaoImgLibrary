package com.example.kakaoimglibrary.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.concurrent.atomic.AtomicLong

class SearchViewModel(private val idGenerate: AtomicLong) : ViewModel() {

    private val _list: MutableLiveData<List<SearchModel>> = MutableLiveData()
    //읽기전용
    val list: LiveData<List<SearchModel>> get() = _list

    init {
        _list.value = arrayListOf<SearchModel>().apply {
            for (i in 0 until 10) {
                add(
                    SearchModel(
                        idGenerate.getAndIncrement(),
                        title = "test $i",
                        datetime = "test:time: $i",
                        url = "https://softychoo.github.io/assets/img/me.JPG",
                        isBookmark = false
                    )
                )
            }
        }
    }

    fun modifySearchItem(searchModel: SearchModel, position: Int){
        val currentList = list.value.orEmpty().toMutableList()
        currentList[position] = searchModel
        _list.value = currentList
    }


}