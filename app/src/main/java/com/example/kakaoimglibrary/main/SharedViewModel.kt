package com.example.kakaoimglibrary.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kakaoimglibrary.bookmark.BookmarkModel
import com.example.kakaoimglibrary.model.ResponseModel
import com.example.kakaoimglibrary.model.toBookmarkModel
import com.example.kakaoimglibrary.search.SearchModel

class SharedViewModel : ViewModel() {
    private val _bookmarkState: MutableLiveData<BookmarkState> = MutableLiveData()
    private val _searchState: MutableLiveData<SearchState> = MutableLiveData()

    // 읽기 전용
    val bookmarkState: LiveData<BookmarkState> get() = _bookmarkState
    val searchState: LiveData<SearchState> get() = _searchState

    fun updateBookmarkState(item : ResponseModel, entryType : String){
        when(entryType){
            EntryType.ADD.name -> _bookmarkState.value = BookmarkState.AddBookmark(item.toBookmarkModel())
            EntryType.REMOVE.name -> _bookmarkState.value =BookmarkState.removeBookmark(item.toBookmarkModel())
        }
    }

//    fun updateSearchState(item : BookmarkModel, entryType: String){
//        when(entryType){
//            EntryType.ADD.name -> Unit
//            EntryType.REMOVE.name -> Unit
//            EntryType.EDIT.name -> _searchState.value = SearchState.modifySearch(item.toSearchModel())
//        }
//    }
}

sealed interface SearchState {
    data class modifySearch(val searchModel: SearchModel) : SearchState
}

sealed interface BookmarkState {
    data class AddBookmark(val bookmarkModel: BookmarkModel) : BookmarkState
    data class removeBookmark(val bookmarkModel: BookmarkModel) : BookmarkState
}