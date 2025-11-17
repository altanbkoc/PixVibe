package com.altankoc.pixvibe.domain.usecase

import com.altankoc.pixvibe.domain.model.Image
import com.altankoc.pixvibe.domain.repository.ImageRepository
import com.altankoc.pixvibe.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchImagesUseCase @Inject constructor(
    private val repository: ImageRepository
) {

    operator fun invoke(query: String, page: Int): Flow<Resource<List<Image>>> {
        return repository.searchImages(query, page)
    }
}