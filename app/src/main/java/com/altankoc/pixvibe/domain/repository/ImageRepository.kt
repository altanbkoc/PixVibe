package com.altankoc.pixvibe.domain.repository

import com.altankoc.pixvibe.domain.model.Image
import com.altankoc.pixvibe.util.Resource
import kotlinx.coroutines.flow.Flow

interface ImageRepository {

    // Remote
    fun searchImages(query: String, page: Int): Flow<Resource<List<Image>>>

    fun getRandomImages(page: Int): Flow<Resource<List<Image>>>

    fun getImagesByCategory(category: String, page: Int): Flow<Resource<List<Image>>>


    suspend fun getImageById(imageId: Int): Image?

    // Local
    suspend fun addToFavorites(image: Image)

    suspend fun removeFromFavorites(image: Image)

    fun getFavoriteImages(): Flow<List<Image>>

    suspend fun getFavoriteImageById(imageId: Int): Image?

    fun isImageFavorite(imageId: Int): Flow<Boolean>

    suspend fun deleteAllFavorites()
}