package com.example.kakaoimglibrary.bookmark

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class BookmarkViewModel : ViewModel() {

    private val _list : MutableLiveData<List<BookmarkModel>> = MutableLiveData()
    //읽기전용
    val list : LiveData<List<BookmarkModel>> get() = _list

    fun addBookmarkModel(bookmarkModel: BookmarkModel){
        val currentList = list.value.orEmpty().toMutableList()
        currentList.add(bookmarkModel)
        _list.value = currentList
    }

    fun removeBookmarkItem(item: BookmarkModel,position: Int?) {
        val currentList = list.value.orEmpty().toMutableList()

        val findPosition = position ?: currentList.findIndex(item) // position이 null일 때 findIndex실행
        if (findPosition == -1){
            return
        }

        currentList.removeAt(findPosition)
        _list.value = currentList
    }

    fun List<BookmarkModel>.findIndex(bookmarkModel: BookmarkModel): Int {
        return indexOfFirst { it.id == bookmarkModel.id } // indexOfFirst : 가장 먼저 찾는 값 | 없으면 -1 return
    }
}