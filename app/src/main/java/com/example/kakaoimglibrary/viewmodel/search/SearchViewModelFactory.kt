package com.example.kakaoimglibrary.viewmodel.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.kakaoimglibrary.data.Repository
import com.example.kakaoimglibrary.data.RetrofitClient
import java.lang.IllegalArgumentException

class SearchViewModelFactory() : ViewModelProvider.Factory {

    private val repository = Repository(RetrofitClient)
    override fun <T: ViewModel> create(modelClass: Class<T>): T {

        if(modelClass.isAssignableFrom(SearchViewModel::class.java)){ // 요청된 모델 클래스 TodoViewModel 클래스랑 호환 되는지 확인
            return SearchViewModel(repository) as T // 호환될 경우 생성된 ViewModel T타입 으로 형변환 하여 반환
        }
        throw IllegalArgumentException("Not Found ViewModel class.") // 호환 되지 않는 경우 알림
    }
}