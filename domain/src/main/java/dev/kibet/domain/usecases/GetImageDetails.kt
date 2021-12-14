package dev.kibet.domain.usecases

import dev.kibet.domain.models.Image
import dev.kibet.domain.repository.ImagesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class GetImageDetails(private val repository: ImagesRepository) {
    suspend operator fun invoke(id: Int): Flow<Image> {
        return flowOf(repository.getImageDetails(id))
    }
}