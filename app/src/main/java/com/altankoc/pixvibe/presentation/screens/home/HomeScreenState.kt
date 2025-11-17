package com.altankoc.pixvibe.presentation.screens.home

import com.altankoc.pixvibe.domain.model.Image

data class HomeScreenState(
    val mostLikedImages: List<Image> = emptyList(),
    val mostDownloadedImages: List<Image> = emptyList(),
    val recommendedImages: List<Image> = emptyList(),
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    val error: String? = null
)