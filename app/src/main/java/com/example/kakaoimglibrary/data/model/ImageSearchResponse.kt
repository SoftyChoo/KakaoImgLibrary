package com.example.kakaoimglibrary.data.model

import com.google.gson.annotations.SerializedName

data class ImageSearchResponse(
    @SerializedName("documents")
    val documents: MutableList<Documents>, // 안정성 : 수정할 수 없게

    @SerializedName("meta")
    val metaData: MetaData?
) {
    data class Documents(
        @SerializedName("image_url")
        val imageUrl: String,

        @SerializedName("display_sitename")
        val displaySitename: String,

        @SerializedName("datetime")
        val datetime: String,
    )

    data class MetaData(
        @SerializedName("total_count") // 검색 된 문서 수
        val totalCount: Int?,

        @SerializedName("pageable_count") // total 중 노출 가능 문서 수
        val pageableCount: Int,

        @SerializedName("is_end") // 현재 페이지가 마지막 페이지 인지의 여부  -> false면 page를 증가시며 다음 페이지를 요청
        val isEnd: Boolean?
    )
}

