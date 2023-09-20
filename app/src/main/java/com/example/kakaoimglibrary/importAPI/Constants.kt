package com.example.kakaoimglibrary.importAPI

class Constants {
    companion object{
        private const val REST_API_KEY = "b4bfb4c98056bc082c48b2031c38710d"

        const val BASE_URL = "https://dapi.kakao.com"

        const val AUTH_HEADER = "KakaoAK $REST_API_KEY"

        const val TYPE_IMAGE = 0

        const val TYPE_VIDEO = 1
        // 개인 API 사용
    }
}