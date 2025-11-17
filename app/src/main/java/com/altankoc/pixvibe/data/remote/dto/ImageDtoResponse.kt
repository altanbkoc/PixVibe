package com.altankoc.pixvibe.data.remote.dto

import com.google.gson.annotations.SerializedName

data class ImageDtoResponse(
    @SerializedName("total")
    val total: Int,

    @SerializedName("totalHits")
    val totalHits: Int,

    @SerializedName("hits")
    val hits: List<ImageDto>
)