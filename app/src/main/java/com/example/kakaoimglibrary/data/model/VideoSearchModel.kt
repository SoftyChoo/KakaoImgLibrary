package com.example.kakaoimglibrary.data.model

import com.google.gson.annotations.SerializedName

data class VideoSearchModel(
    @SerializedName("documents")
    val documents: MutableList<Documents>, // 안정성 : 수정할 수 없게

    @SerializedName("meta")
    val metaData: MetaData?
) {
    data class Documents(

        val title: String,

        val datetime: String,

        val thumbnail: String,
    )

    data class MetaData(
        @SerializedName("total_count") // 검색 된 문서 수
        val total_count: Int?,

        @SerializedName("pageable_count") // total 중 노출 가능 문서 수
        val pageable_count: Int,

        @SerializedName("is_end") // 현재 페이지가 마지막 페이지 인지의 여부  -> false면 page를 증가시며 다음 페이지를 요청
        val is_end: Boolean?
    )
}

