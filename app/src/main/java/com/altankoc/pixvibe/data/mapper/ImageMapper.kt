package com.altankoc.pixvibe.data.mapper

import com.altankoc.pixvibe.data.local.entity.ImageEntity
import com.altankoc.pixvibe.data.remote.dto.ImageDto
import com.altankoc.pixvibe.domain.model.Image


// Remote
fun ImageDto.toImage(): Image {
    return Image(
        id = id,
        pageUrl = pageUrl,
        type = type,
        tags = tags.split(",").map { it.trim() },
        previewUrl = previewUrl,
        previewWidth = previewWidth,
        previewHeight = previewHeight,
        webformatUrl = webformatUrl,
        webformatWidth = webformatWidth,
        webformatHeight = webformatHeight,
        largeImageUrl = largeImageUrl,
        imageWidth = imageWidth,
        imageHeight = imageHeight,
        imageSize = imageSize,
        downloads = downloads,
        likes = likes
    )
}

fun List<ImageDto>.toImageList(): List<Image> {
    return map { it.toImage() }
}

// Local

fun ImageEntity.toImage(): Image {
    return Image(
        id = id,
        pageUrl = pageUrl,
        type = type,
        tags = tags.split(",").map { it.trim() },
        previewUrl = previewUrl,
        previewWidth = previewWidth,
        previewHeight = previewHeight,
        webformatUrl = webformatUrl,
        webformatWidth = webformatWidth,
        webformatHeight = webformatHeight,
        largeImageUrl = largeImageUrl,
        imageWidth = imageWidth,
        imageHeight = imageHeight,
        imageSize = imageSize,
        downloads = downloads,
        likes = likes
    )
}

fun List<ImageEntity>.toImageListFromEntity(): List<Image> {
    return map { it.toImage() }
}

fun Image.toImageEntity(): ImageEntity {
    return ImageEntity(
        id = id,
        pageUrl = pageUrl,
        type = type,
        tags = tags.joinToString(","),
        previewUrl = previewUrl,
        previewWidth = previewWidth,
        previewHeight = previewHeight,
        webformatUrl = webformatUrl,
        webformatWidth = webformatWidth,
        webformatHeight = webformatHeight,
        largeImageUrl = largeImageUrl,
        imageWidth = imageWidth,
        imageHeight = imageHeight,
        imageSize = imageSize,
        downloads = downloads,
        likes = likes
    )
}