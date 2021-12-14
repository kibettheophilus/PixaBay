package dev.kibet.data_remote.mappers

import dev.kibet.data_local.entities.ImageEntity
import dev.kibet.domain.models.Image

fun Image.toEntity(): ImageEntity {
    return ImageEntity(
        comments,
        downloads,
        id,
        likes,
        user,
        userImageURL,
        views,
        webformatURL,
    )
}
