package com.altankoc.pixvibe.presentation.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.altankoc.pixvibe.domain.usecase.GetRandomImagesUseCase
import com.altankoc.pixvibe.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getRandomImagesUseCase: GetRandomImagesUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(HomeScreenState())
    val state: StateFlow<HomeScreenState> = _state.asStateFlow()

    init {
        loadAllImages()
    }

    fun loadAllImages() {
        loadMostLikedImages()
        loadMostDownloadedImages()
        loadRecommendedImages()
    }

    fun refresh() {
        _state.update { it.copy(isRefreshing = true) }
        loadAllImages()
    }

    private fun loadMostLikedImages() {
        viewModelScope.launch {

            val randomPage = Random.nextInt(1, 11)

            getRandomImagesUseCase(page = randomPage).collect { resource ->
                when (resource) {
                    is Resource.Loading -> {
                        _state.update { it.copy(isLoading = true, error = null) }
                    }
                    is Resource.Success -> {
                        val sortedByLikes = resource.data.sortedByDescending { it.likes }
                        _state.update {
                            it.copy(
                                mostLikedImages = sortedByLikes,
                                isLoading = false,
                                isRefreshing = false,
                                error = null
                            )
                        }
                    }
                    is Resource.Error -> {
                        _state.update {
                            it.copy(
                                isLoading = false,
                                isRefreshing = false,
                                error = resource.message
                            )
                        }
                    }
                }
            }
        }
    }

    private fun loadMostDownloadedImages() {
        viewModelScope.launch {
            val randomPage = Random.nextInt(11, 21)

            getRandomImagesUseCase(page = randomPage).collect { resource ->
                when (resource) {
                    is Resource.Success -> {
                        val sortedByDownloads = resource.data.sortedByDescending { it.downloads }
                        _state.update {
                            it.copy(mostDownloadedImages = sortedByDownloads)
                        }
                    }
                    is Resource.Error -> {
                    }
                    is Resource.Loading -> {
                    }
                }
            }
        }
    }

    private fun loadRecommendedImages() {
        viewModelScope.launch {
            val randomPage = Random.nextInt(21, 31)

            getRandomImagesUseCase(page = randomPage).collect { resource ->
                when (resource) {
                    is Resource.Success -> {
                        _state.update {
                            it.copy(recommendedImages = resource.data)
                        }
                    }
                    is Resource.Error -> {
                    }
                    is Resource.Loading -> {
                    }
                }
            }
        }
    }
}