package com.example.kakaoimglibrary.viewmodel.bookmark

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

class BookmarkViewModelFactory : ViewModelProvider.Factory{

    override fun <T: ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(BookmarkViewModel::class.java)){
            return BookmarkViewModel() as T
        }
        throw IllegalArgumentException("Not Found ViewModel class.")
    }
}