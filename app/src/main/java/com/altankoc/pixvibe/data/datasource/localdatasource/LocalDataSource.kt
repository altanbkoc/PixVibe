package com.altankoc.pixvibe.data.datasource.localdatasource

import com.altankoc.pixvibe.data.local.entity.ImageEntity
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {

    suspend fun insertFavoriteImage(image: ImageEntity)

    suspend fun deleteFavoriteImage(image: ImageEntity)

    fun getAllFavoriteImages(): Flow<List<ImageEntity>>

    suspend fun getImageById(imageId: Int): ImageEntity?

    fun isImageFavorite(imageId: Int): Flow<Boolean>

    suspend fun deleteAllFavorites()
}