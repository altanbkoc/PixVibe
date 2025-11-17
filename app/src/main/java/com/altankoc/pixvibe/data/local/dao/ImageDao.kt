package com.altankoc.pixvibe.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.altankoc.pixvibe.data.local.entity.ImageEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ImageDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertImage(image: ImageEntity)

    @Delete
    suspend fun deleteImage(image: ImageEntity)

    @Query("SELECT * FROM favorite_images ORDER BY savedAt DESC")
    fun getAllFavoriteImages(): Flow<List<ImageEntity>>

    @Query("SELECT * FROM favorite_images WHERE id = :imageId")
    suspend fun getImageById(imageId: Int): ImageEntity?

    @Query("SELECT EXISTS(SELECT 1 FROM favorite_images WHERE id = :imageId)")
    fun isImageFavorite(imageId: Int): Flow<Boolean>

    @Query("DELETE FROM favorite_images")
    suspend fun deleteAllFavorites()


}