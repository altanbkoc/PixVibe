package com.altankoc.pixvibe.domain.usecase

import com.altankoc.pixvibe.domain.repository.ImageRepository
import javax.inject.Inject

class DeleteAllFavoritesUseCase @Inject constructor(
    private val repository: ImageRepository
) {
    suspend operator fun invoke() {
        repository.deleteAllFavorites()
    }
}