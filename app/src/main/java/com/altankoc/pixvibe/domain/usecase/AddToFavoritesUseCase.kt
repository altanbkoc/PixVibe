package com.altankoc.pixvibe.domain.usecase

import com.altankoc.pixvibe.domain.model.Image
import com.altankoc.pixvibe.domain.repository.ImageRepository
import javax.inject.Inject

class AddToFavoritesUseCase @Inject constructor(
    private val repository: ImageRepository
) {
    suspend operator fun invoke(image: Image) {
        repository.addToFavorites(image)
    }
}