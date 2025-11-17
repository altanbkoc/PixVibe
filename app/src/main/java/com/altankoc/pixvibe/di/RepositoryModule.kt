package com.altankoc.pixvibe.di

import android.content.Context
import com.altankoc.pixvibe.data.datasource.localdatasource.LocalDataSource
import com.altankoc.pixvibe.data.datasource.remotedatasource.RemoteDataSource
import com.altankoc.pixvibe.data.repository.ImageRepositoryImpl
import com.altankoc.pixvibe.domain.repository.ImageRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideImageRepository(
        remoteDataSource: RemoteDataSource,
        localDataSource: LocalDataSource,
        @ApplicationContext context: Context
    ): ImageRepository {
        return ImageRepositoryImpl(remoteDataSource, localDataSource, context)
    }
}