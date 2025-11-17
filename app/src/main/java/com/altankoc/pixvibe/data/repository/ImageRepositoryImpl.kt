package com.altankoc.pixvibe.data.repository

import android.content.Context
import com.altankoc.pixvibe.R
import com.altankoc.pixvibe.data.datasource.localdatasource.LocalDataSource
import com.altankoc.pixvibe.data.datasource.remotedatasource.RemoteDataSource
import com.altankoc.pixvibe.data.mapper.toImage
import com.altankoc.pixvibe.data.mapper.toImageEntity
import com.altankoc.pixvibe.data.mapper.toImageList
import com.altankoc.pixvibe.data.mapper.toImageListFromEntity
import com.altankoc.pixvibe.domain.model.Image
import com.altankoc.pixvibe.domain.repository.ImageRepository
import com.altankoc.pixvibe.util.Resource
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ImageRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    @ApplicationContext private val context: Context
) : ImageRepository {


    private val imageCache = mutableMapOf<Int, Image>()

    override fun searchImages(query: String, page: Int): Flow<Resource<List<Image>>> = flow {
        try {
            emit(Resource.Loading)
            val response = remoteDataSource.searchImages(query, page)
            val images = response.hits.toImageList()


            images.forEach { imageCache[it.id] = it }

            emit(Resource.Success(images))
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: context.getString(R.string.error_search)))
        }
    }

    override fun getRandomImages(page: Int): Flow<Resource<List<Image>>> = flow {
        try {
            emit(Resource.Loading)
            val response = remoteDataSource.getRandomImages(page)
            val images = response.hits.toImageList()


            images.forEach { imageCache[it.id] = it }

            emit(Resource.Success(images))
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: context.getString(R.string.error_fetch_random)))
        }
    }

    override fun getImagesByCategory(category: String, page: Int): Flow<Resource<List<Image>>> = flow {
        try {
            emit(Resource.Loading)
            val response = remoteDataSource.getImagesByCategory(category, page)
            val images = response.hits.toImageList()


            images.forEach { imageCache[it.id] = it }

            emit(Resource.Success(images))
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: context.getString(R.string.error_fetch_category)))
        }
    }

    override suspend fun getImageById(imageId: Int): Image? {

        imageCache[imageId]?.let { return it }


        return localDataSource.getImageById(imageId)?.toImage()
    }

    override suspend fun addToFavorites(image: Image) {
        val imageEntity = image.toImageEntity()
        localDataSource.insertFavoriteImage(imageEntity)
    }

    override suspend fun removeFromFavorites(image: Image) {
        val imageEntity = image.toImageEntity()
        localDataSource.deleteFavoriteImage(imageEntity)
    }

    override fun getFavoriteImages(): Flow<List<Image>> {
        return localDataSource.getAllFavoriteImages().map { entityList ->
            entityList.toImageListFromEntity()
        }
    }

    override suspend fun getFavoriteImageById(imageId: Int): Image? {
        val imageEntity = localDataSource.getImageById(imageId)
        return imageEntity?.toImage()
    }

    override fun isImageFavorite(imageId: Int): Flow<Boolean> {
        return localDataSource.isImageFavorite(imageId)
    }

    override suspend fun deleteAllFavorites() {
        localDataSource.deleteAllFavorites()
    }
}