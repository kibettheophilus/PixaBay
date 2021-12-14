package dev.kibet.data_remote.mappers

import dev.kibet.data_remote.remote.models.HitDto
import dev.kibet.data_remote.remote.models.ImagesDto
import dev.kibet.domain.models.Image
import dev.kibet.domain.models.Images

fun ImagesDto.toDomain(): Images {
    return Images(
        hits = hits.map { it.toDomain() }
    )
}

fun HitDto.toDomain(): Image {
    return Image(
        imageUrl = webformatURL,
        owner = user
    )
}