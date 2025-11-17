package com.altankoc.pixvibe.presentation.screens.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.altankoc.pixvibe.domain.usecase.DeleteAllFavoritesUseCase
import com.altankoc.pixvibe.domain.usecase.GetFavoriteImagesUseCase
import com.altankoc.pixvibe.domain.usecase.RemoveFromFavoritesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val getFavoriteImagesUseCase: GetFavoriteImagesUseCase,
    private val removeFromFavoritesUseCase: RemoveFromFavoritesUseCase,
    private val deleteAllFavoritesUseCase: DeleteAllFavoritesUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(FavoritesScreenState())
    val state: StateFlow<FavoritesScreenState> = _state.asStateFlow()

    init {
        loadFavorites()
    }

    private fun loadFavorites() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }

            getFavoriteImagesUseCase()
                .catch { e ->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            error = e.localizedMessage
                        )
                    }
                }
                .collect { images ->
                    _state.update {
                        it.copy(
                            favoriteImages = images,
                            isLoading = false,
                            error = null
                        )
                    }
                }
        }
    }

    fun removeFromFavorites(image: com.altankoc.pixvibe.domain.model.Image) {
        viewModelScope.launch {
            removeFromFavoritesUseCase(image)
        }
    }

    fun showDeleteAllDialog() {
        _state.update { it.copy(showDeleteDialog = true) }
    }

    fun hideDeleteAllDialog() {
        _state.update { it.copy(showDeleteDialog = false) }
    }

    fun deleteAllFavorites() {
        viewModelScope.launch {
            deleteAllFavoritesUseCase()
            _state.update { it.copy(showDeleteDialog = false) }
        }
    }
}