package com.altankoc.pixvibe.presentation.screens.detail

import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import android.os.Environment
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.altankoc.pixvibe.R
import com.altankoc.pixvibe.domain.usecase.AddToFavoritesUseCase
import com.altankoc.pixvibe.domain.usecase.GetImageByIdUseCase
import com.altankoc.pixvibe.domain.usecase.IsImageFavoriteUseCase
import com.altankoc.pixvibe.domain.usecase.RemoveFromFavoritesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ImageDetailViewModel @Inject constructor(
    private val getImageByIdUseCase: GetImageByIdUseCase,
    private val addToFavoritesUseCase: AddToFavoritesUseCase,
    private val removeFromFavoritesUseCase: RemoveFromFavoritesUseCase,
    private val isImageFavoriteUseCase: IsImageFavoriteUseCase,
    @ApplicationContext private val context: Context,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = MutableStateFlow(ImageDetailScreenState())
    val state: StateFlow<ImageDetailScreenState> = _state.asStateFlow()

    private val imageId: Int = savedStateHandle.get<Int>("imageId") ?: 0

    init {
        loadImageDetails()
        observeFavoriteStatus()
    }

    private fun loadImageDetails() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }

            val image = getImageByIdUseCase(imageId)

            _state.update {
                it.copy(
                    image = image,
                    isLoading = false,
                    error = if (image == null) context.getString(R.string.error_loading) else null
                )
            }
        }
    }

    private fun observeFavoriteStatus() {
        viewModelScope.launch {
            isImageFavoriteUseCase(imageId).collect { isFavorite ->
                _state.update { it.copy(isFavorite = isFavorite) }
            }
        }
    }

    fun toggleFavorite() {
        viewModelScope.launch {
            val image = _state.value.image ?: return@launch

            if (_state.value.isFavorite) {
                removeFromFavoritesUseCase(image)
                showMessage(context.getString(R.string.detail_removed))
            } else {
                addToFavoritesUseCase(image)
                showMessage(context.getString(R.string.detail_saved))
            }
        }
    }

    fun downloadImage() {
        viewModelScope.launch {
            val image = _state.value.image ?: return@launch

            try {
                val downloadManager = context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
                val request = DownloadManager.Request(Uri.parse(image.largeImageUrl))
                    .setTitle("PixVibe - ${image.id}")
                    .setDescription(context.getString(R.string.loading_images))
                    .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                    .setDestinationInExternalPublicDir(
                        Environment.DIRECTORY_PICTURES,
                        "PixVibe_${image.id}.jpg"
                    )
                    .setAllowedOverMetered(true)
                    .setAllowedOverRoaming(true)

                downloadManager.enqueue(request)
                showMessage(context.getString(R.string.detail_downloaded))
            } catch (e: Exception) {
                showMessage(context.getString(R.string.error_unknown))
            }
        }
    }

    private fun showMessage(message: String) {
        _state.update { it.copy(showMessage = message) }
    }

    fun clearMessage() {
        _state.update { it.copy(showMessage = null) }
    }
}