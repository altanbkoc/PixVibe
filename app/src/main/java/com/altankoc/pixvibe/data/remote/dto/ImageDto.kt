package com.altankoc.pixvibe.data.remote.dto

import com.google.gson.annotations.SerializedName

data class ImageDto(
    @SerializedName("id")
    val id: Int,

    @SerializedName("pageURL")
    val pageUrl: String,

    @SerializedName("type")
    val type: String,

    @SerializedName("tags")
    val tags: String,

    @SerializedName("previewURL")
    val previewUrl: String,

    @SerializedName("previewWidth")
    val previewWidth: Int,

    @SerializedName("previewHeight")
    val previewHeight: Int,

    @SerializedName("webformatURL")
    val webformatUrl: String,

    @SerializedName("webformatWidth")
    val webformatWidth: Int,

    @SerializedName("webformatHeight")
    val webformatHeight: Int,

    @SerializedName("largeImageURL")
    val largeImageUrl: String,

    @SerializedName("imageWidth")
    val imageWidth: Int,

    @SerializedName("imageHeight")
    val imageHeight: Int,

    @SerializedName("imageSize")
    val imageSize: Int,

    @SerializedName("downloads")
    val downloads: Int,

    @SerializedName("likes")
    val likes: Int
)