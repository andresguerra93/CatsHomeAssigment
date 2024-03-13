package com.agc.catshomeassignmet.data.core.mappers

import com.agc.catshomeassignmet.data.local.entities.CatEntity
import com.agc.catshomeassignmet.data.network.dto.CatResponseDto
import com.agc.catshomeassignmet.data.network.dto.CatsResponseDto
import com.agc.catshomeassignmet.domain.model.Cat
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

fun CatsResponseDto.toDomain(): Cat {
    return Cat(
        id = this.id,
        owner = "Without Owner",
        tags = this.tags?: arrayListOf("Without Tags"),
        size = this.size?: 0,
        mimeType = this.mimeType?: "Without mimeType",
        createdAt = "Without Date Of Creation",
        updatedAt = "Without Date Of Update",
    )
}
fun CatResponseDto.toDomain(): Cat {
    return Cat(
        id = this.id,
        owner = "Without Owner",
        tags = this.tags?: arrayListOf("Without Tags"),
        size = this.size?: 0,
        mimeType = this.mimeType?: "Without mimeType",
        createdAt = if(this.createdAt.isNullOrEmpty()){"Error in the Date"}else{dateFormaterCatResponseDto(this.createdAt)?: "Error in the Date"},
        updatedAt = if(this.updatedAt.isNullOrEmpty()){"Error in the Date"}else{dateFormaterCatResponseDto(this.updatedAt)?: "Error in the Date"}
    )
}
fun CatEntity.toDomain(): Cat {
    return Cat(
        id = this.id,
        owner = if(this.owner == "null"){ "Without Owner"}else{this.owner},
        tags = if(this.tags.isEmpty()){ listOf("Without Tags")}else{this.tags},
        size = 0,
        mimeType = "Without Type",
        createdAt = if(this.createdAt.isNullOrEmpty()){"Error in the Date"}else{ dateFormaterCatEntity(this.createdAt) ?: "Error in the Date"},
        updatedAt = if(this.updatedAt.isNullOrEmpty()){"Error in the Date"}else{dateFormaterCatEntity(this.updatedAt)?: "Error in the Date"}
    )
}

fun dateFormaterCatEntity(date: String): String {

    try {
    // Parse the input date string as a LocalDateTime
    val formatter = DateTimeFormatter.ofPattern("EEE MMM dd yyyy HH:mm:ss 'GMT'Z (zzzz)")
    val localDateTime = LocalDateTime.parse(date, formatter)

    // Convert the LocalDateTime to a ZonedDateTime in UTC (opcional)
    val parsedDate = ZonedDateTime.of(localDateTime, ZoneId.of("UTC"))

    // Format the output date string
    val outputFormatter = DateTimeFormatter.ofPattern("EEE, dd MMM yyyy")
    return parsedDate.format(outputFormatter)
    } catch (e: Exception) {
        return "Error in the parse date in CatResponseDto.toDomain"
    }


}
fun dateFormaterCatResponseDto(date: String): String {

    try {
    // Parse the input date string as a LocalDateTime
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    val localDateTime = LocalDateTime.parse(date, formatter)

    // Convert the LocalDateTime to a ZonedDateTime in UTC (opcional)
    val parsedDate = ZonedDateTime.of(localDateTime, ZoneId.of("UTC"))

    // Format the output date string
    val outputFormatter = DateTimeFormatter.ofPattern("EEE, dd MMM yyyy")
    return parsedDate.format(outputFormatter)
    } catch (e: Exception) {
        return "Error in the parse date in CatResponseDto.toDomain"
    }

}