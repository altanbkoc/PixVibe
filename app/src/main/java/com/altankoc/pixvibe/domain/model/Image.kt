package com.altankoc.pixvibe.domain.model

data class Image(
    val id: Int,
    val pageUrl: String,
    val type: String,
    val tags: List<String>,
    val previewUrl: String,
    val previewWidth: Int,
    val previewHeight: Int,
    val webformatUrl: String,
    val webformatWidth: Int,
    val webformatHeight: Int,
    val largeImageUrl: String,
    val imageWidth: Int,
    val imageHeight: Int,
    val imageSize: Int,
    val downloads: Int,
    val likes: Int
)