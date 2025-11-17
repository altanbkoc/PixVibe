package com.altankoc.pixvibe.domain.usecase

import com.altankoc.pixvibe.domain.repository.ImageRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class IsImageFavoriteUseCase @Inject constructor(
    private val repository: ImageRepository
) {
    operator fun invoke(imageId: Int): Flow<Boolean> {
        return repository.isImageFavorite(imageId)
    }
}