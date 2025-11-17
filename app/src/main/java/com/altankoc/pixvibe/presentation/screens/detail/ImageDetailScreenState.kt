package com.altankoc.pixvibe.presentation.screens.detail

import com.altankoc.pixvibe.domain.model.Image

data class ImageDetailScreenState(
    val image: Image? = null,
    val isFavorite: Boolean = false,
    val isLoading: Boolean = false,
    val error: String? = null,
    val downloadProgress: Float? = null,
    val showMessage: String? = null
)