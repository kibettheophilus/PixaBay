package dev.kibet.data_remote.mappers

import dev.kibet.data_local.entities.ImageEntity
import dev.kibet.domain.models.Image

fun ImageEntity.toDomain(): Image {
    return Image(
        collections,
        comments,
        downloads,
        id,
        imageHeight,
        imageSize,
        imageWidth,
        largeImageURL,
        likes,
        pageURL,
        previewHeight,
        previewURL,
        previewWidth,
        tags,
        type,
        user,
        userImageURL,
        user_id,
        views,
        webformatHeight,
        webformatURL,
        webformatWidth
    )
}
