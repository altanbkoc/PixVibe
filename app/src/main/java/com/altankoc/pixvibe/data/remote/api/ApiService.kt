package com.altankoc.pixvibe.data.remote.api

import com.altankoc.pixvibe.BuildConfig
import com.altankoc.pixvibe.data.remote.dto.ImageDtoResponse
import com.altankoc.pixvibe.util.Constants
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET(".")
    suspend fun searchImages(
        @Query("key") apiKey: String = BuildConfig.PIXABAY_API_KEY,
        @Query("q") query: String,
        @Query("image_type") imageType: String = "photo",
        @Query("per_page") perPage: Int = Constants.PAGE_SIZE,
        @Query("page") page: Int = Constants.INITIAL_PAGE
    ): ImageDtoResponse

    @GET(".")
    suspend fun getRandomImages(
        @Query("key") apiKey: String = BuildConfig.PIXABAY_API_KEY,
        @Query("image_type") imageType: String = "photo",
        @Query("per_page") perPage: Int = Constants.PAGE_SIZE,
        @Query("page") page: Int = Constants.INITIAL_PAGE,
        @Query("order") order: String = "latest"
    ): ImageDtoResponse

    @GET(".")
    suspend fun getImagesByCategory(
        @Query("key") apiKey: String = BuildConfig.PIXABAY_API_KEY,
        @Query("category") category: String,
        @Query("image_type") imageType: String = "photo",
        @Query("per_page") perPage: Int = Constants.PAGE_SIZE,
        @Query("page") page: Int = Constants.INITIAL_PAGE
    ): ImageDtoResponse
}