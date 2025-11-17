package com.altankoc.pixvibe.data.datasource.remotedatasource

import com.altankoc.pixvibe.data.remote.dto.ImageDtoResponse

interface RemoteDataSource {
    suspend fun searchImages(
        query: String,
        page: Int
    ): ImageDtoResponse

    suspend fun getRandomImages(
        page: Int
    ): ImageDtoResponse

    suspend fun getImagesByCategory(
        category: String,
        page: Int
    ): ImageDtoResponse
}