package com.altankoc.pixvibe.domain.usecase

import com.altankoc.pixvibe.domain.model.Image
import com.altankoc.pixvibe.domain.repository.ImageRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFavoriteImagesUseCase @Inject constructor(
    private val repository: ImageRepository
) {
    operator fun invoke(): Flow<List<Image>> {
        return repository.getFavoriteImages()
    }
}