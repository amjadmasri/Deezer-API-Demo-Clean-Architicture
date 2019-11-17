package com.amjad.deezerchallange.data.models

import com.google.gson.annotations.SerializedName

data class PagedListResponse<T>(
    @SerializedName("data")
    val dataList: List<T>,
    @SerializedName("total")
    val total:Int,
    @SerializedName("next")
    val nextUrl:String
)