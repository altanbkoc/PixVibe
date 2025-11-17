package com.altankoc.pixvibe.data.datasource.remotedatasource

import com.altankoc.pixvibe.data.remote.api.ApiService
import com.altankoc.pixvibe.data.remote.dto.ImageDtoResponse
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val apiService: ApiService
) : RemoteDataSource {

    override suspend fun searchImages(
        query: String,
        page: Int
    ): ImageDtoResponse {
        return apiService.searchImages(
            query = query,
            page = page
        )
    }

    override suspend fun getRandomImages(page: Int): ImageDtoResponse {
        return apiService.getRandomImages(
            page = page
        )
    }

    override suspend fun getImagesByCategory(
        category: String,
        page: Int
    ): ImageDtoResponse {
        return apiService.getImagesByCategory(
            category = category,
            page = page
        )
    }

}