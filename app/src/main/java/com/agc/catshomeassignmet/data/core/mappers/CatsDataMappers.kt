package com.agc.catshomeassignmet.data.core.mappers

import com.agc.catshomeassignmet.data.local.entities.CatEntity
import com.agc.catshomeassignmet.data.network.dto.CatsResponseDto
import com.agc.catshomeassignmet.domain.model.Cat
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

fun CatsResponseDto.toDomain(): Cat {
    return Cat(
        id = this.id,
        owner = "Without Owner",
        tags = this.tags,
        size = this.size,
        mimeType = this.mimeType,
        createdAt = "Without Date Of Creation",
        updatedAt = "Without Date Of Update",
    )
}

fun CatEntity.toDomain(): Cat {
    return Cat(
        id = this.id,
        owner = this.owner,
        tags = this.tags,
        size = -1,
        mimeType = "Without Type",
        createdAt = dateFormater(this.createdAt),
        updatedAt = dateFormater(this.updatedAt)
    )
}

fun dateFormater(date: String): String {
    val formatter = DateTimeFormatter.ofPattern("EEE MMM dd yyyy HH:mm:ss 'GMT'Z (zzzz)")
    val parsedDate = ZonedDateTime.parse(date, formatter)
    val outputFormatter = DateTimeFormatter.ofPattern("EEE, dd, MMM, yyyy")
    return parsedDate.format(outputFormatter)
}