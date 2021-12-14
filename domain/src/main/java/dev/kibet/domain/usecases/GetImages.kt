package dev.kibet.domain.usecases

import dev.kibet.domain.models.Image
import dev.kibet.domain.repository.ImagesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class GetImages(private val repository: ImagesRepository) {
    suspend operator fun invoke(keyWord: String): Flow<List<Image>> {
        return flowOf(repository.getImages(keyWord))
    }
}