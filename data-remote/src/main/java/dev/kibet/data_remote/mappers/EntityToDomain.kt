package dev.kibet.data_remote.mappers

import dev.kibet.data_local.entities.ImageEntity
import dev.kibet.domain.models.Image

fun ImageEntity.toDomain(): Image {
    return Image(
        comments, downloads, id, likes, user, userImageURL, views, webformatURL
    )
}
