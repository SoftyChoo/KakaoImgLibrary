package com.example.kakaoimglibrary.viewmodel.bookmark

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kakaoimglibrary.model.BookmarkModel

class BookmarkViewModel : ViewModel() {

    private val _list: MutableLiveData<List<BookmarkModel>> = MutableLiveData()
    val list: LiveData<List<BookmarkModel>> get() = _list

    fun addBookmarkModel(bookmarkModel: BookmarkModel) {
        val currentList = list.value.orEmpty().toMutableList()
        currentList.add(bookmarkModel)
        _list.value = currentList
    }

    fun removeBookmarkItem(item: BookmarkModel, position: Int?) {
        val currentList = list.value.orEmpty().toMutableList()
        val findPosition = position ?: currentList.findIndex(item) // position이 null일 때 findIndex 실행
        if (findPosition == -1) {
            return
        }
        currentList.removeAt(findPosition)
        _list.value = currentList
    }

    fun List<BookmarkModel>.findIndex(bookmarkModel: BookmarkModel): Int {
        // indexOfFirst : 가장 먼저 찾는 값 | 없으면 -1 return
        return indexOfFirst { it.thumbnailUri == bookmarkModel.thumbnailUri }
    }

    fun loadBookmarkModel(it: List<BookmarkModel>) {
        _list.value = it
    }
}