package com.altankoc.pixvibe.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.altankoc.pixvibe.data.local.dao.ImageDao
import com.altankoc.pixvibe.data.local.entity.ImageEntity

@Database(entities = [ImageEntity::class], version = 1, exportSchema = false)
abstract class ImageDatabase : RoomDatabase() {
    abstract fun imageDao(): ImageDao
}