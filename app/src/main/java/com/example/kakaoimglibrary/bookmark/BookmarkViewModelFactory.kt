package com.example.kakaoimglibrary.bookmark

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

class BookmarkViewModelFactory : ViewModelProvider.Factory{

    // ViewModelProvider.Factory 인터페이스를 구현하기 위해 create 메서드를 오버라이드
    // 이 메서드는 ViewModel 인스턴스를 생성하는 역할을 함
    override fun <T: ViewModel> create(modelClass: Class<T>): T {
        // create 메서드는 요청된 ViewModel 클래스 (modelClass)를 기반으로 ViewModel을 생성한다.

        if(modelClass.isAssignableFrom(BookmarkViewModel::class.java)){ // 요청된 modelClass가 TodoViewModel Class와 호환 가능한지 확인
            return BookmarkViewModel() as T //호환되는 경우 생성된 ViewModel을 T타입으로 형변환 하여 반환
        }
        throw IllegalArgumentException("Not Found ViewModel class.") // 호환되지 않는 경우 알림
    }
}