package com.altankoc.pixvibe.data.datasource.localdatasource

import com.altankoc.pixvibe.data.local.dao.ImageDao
import com.altankoc.pixvibe.data.local.entity.ImageEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(
    private val imageDao: ImageDao
) : LocalDataSource{

    override suspend fun insertFavoriteImage(image: ImageEntity) {
        imageDao.insertImage(image)
    }

    override suspend fun deleteFavoriteImage(image: ImageEntity) {
        imageDao.deleteImage(image)
    }

    override fun getAllFavoriteImages(): Flow<List<ImageEntity>> {
        return imageDao.getAllFavoriteImages()
    }

    override suspend fun getImageById(imageId: Int): ImageEntity? {
        return imageDao.getImageById(imageId)
    }

    override fun isImageFavorite(imageId: Int): Flow<Boolean> {
        return imageDao.isImageFavorite(imageId)
    }

    override suspend fun deleteAllFavorites() {
        imageDao.deleteAllFavorites()
    }
}