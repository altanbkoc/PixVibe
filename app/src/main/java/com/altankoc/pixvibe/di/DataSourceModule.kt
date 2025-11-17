package com.altankoc.pixvibe.di

import com.altankoc.pixvibe.data.datasource.localdatasource.LocalDataSource
import com.altankoc.pixvibe.data.datasource.localdatasource.LocalDataSourceImpl
import com.altankoc.pixvibe.data.datasource.remotedatasource.RemoteDataSource
import com.altankoc.pixvibe.data.datasource.remotedatasource.RemoteDataSourceImpl
import com.altankoc.pixvibe.data.local.dao.ImageDao
import com.altankoc.pixvibe.data.remote.api.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Provides
    @Singleton
    fun provideRemoteDataSource(
        apiService: ApiService
    ): RemoteDataSource {
        return RemoteDataSourceImpl(apiService)
    }

    @Provides
    @Singleton
    fun provideLocalDataSource(
        imageDao: ImageDao
    ): LocalDataSource {
        return LocalDataSourceImpl(imageDao)
    }
}