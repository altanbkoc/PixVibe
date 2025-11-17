package com.altankoc.pixvibe.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_images")
data class ImageEntity(

    @PrimaryKey
    val id: Int,
    val pageUrl: String,
    val type: String,
    val tags: String,
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
    val likes: Int,
    val savedAt: Long = System.currentTimeMillis()

)