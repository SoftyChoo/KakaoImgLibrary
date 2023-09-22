package com.example.kakaoimglibrary.common

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kakaoimglibrary.model.BookmarkModel
import com.example.kakaoimglibrary.model.toSearchModel
import com.example.kakaoimglibrary.model.SearchModel
import com.example.kakaoimglibrary.model.toBookmarkModel

class SharedViewModel : ViewModel() {
    private val _bookmarkState: MutableLiveData<BookmarkState> = MutableLiveData()
    private val _searchState: MutableLiveData<SearchState> = MutableLiveData()

    val bookmarkState: LiveData<BookmarkState> get() = _bookmarkState
    val searchState: LiveData<SearchState> get() = _searchState

    fun updateBookmarkState(item: SearchModel, entryType: String) {
        when (entryType) {
            EntryType.ADD.name -> _bookmarkState.value =
                BookmarkState.AddBookmark(item.toBookmarkModel())

            EntryType.REMOVE.name -> _bookmarkState.value =
                BookmarkState.RemoveBookmark(item.toBookmarkModel())
        }
    }

    fun updateSearchState(item: BookmarkModel, entryType: String) {
        when (entryType) {
            EntryType.ADD.name -> Unit
            EntryType.REMOVE.name -> Unit
            EntryType.EDIT.name -> _searchState.value =
                SearchState.ModifySearch(item.toSearchModel())
        }
    }
}

sealed interface SearchState {
    data class ModifySearch(val searchModel: SearchModel) : SearchState
}

sealed interface BookmarkState {
    data class AddBookmark(val bookmarkModel: BookmarkModel) : BookmarkState
    data class RemoveBookmark(val bookmarkModel: BookmarkModel) : BookmarkState
}