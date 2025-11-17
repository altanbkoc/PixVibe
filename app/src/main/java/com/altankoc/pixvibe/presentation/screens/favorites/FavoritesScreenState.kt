package com.altankoc.pixvibe.presentation.screens.favorites

import com.altankoc.pixvibe.domain.model.Image

data class FavoritesScreenState(
    val favoriteImages: List<Image> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null,
    val showDeleteDialog: Boolean = false
)