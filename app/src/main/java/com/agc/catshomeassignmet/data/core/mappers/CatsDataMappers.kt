package com.agc.catshomeassignmet.data.core.mappers

import com.agc.catshomeassignmet.data.local.entities.CatEntity
import com.agc.catshomeassignmet.data.network.dto.CatResponseDto
import com.agc.catshomeassignmet.data.network.dto.CatsResponseDto
import com.agc.catshomeassignmet.domain.model.Cat
import java.time.LocalDateTime
import java.time.OffsetDateTime
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

fun CatsResponseDto.toDomain(): Cat {
    return Cat(
        id = this.id,
        owner = "Without Owner",
        tags = this.tags.ifEmpty { arrayListOf("Without Tags") },
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
        tags = if(this.tags.isNullOrEmpty()){ arrayListOf("Without Tags")}else{this.tags},
        size = this.size?: 0,
        mimeType = if(this.mimeType.isNullOrEmpty()){"Without mimeType"}else{this.mimeType},
        createdAt = if(this.createdAt.isNullOrEmpty() || this.createdAt == "Error in the parse date in CatResponseDto.toDomain" ){"Error in the Date"}else{dateFormaterCatResponseDto(this.createdAt)},
        updatedAt = if(this.updatedAt.isNullOrEmpty() || this.createdAt == "Error in the parse date in CatResponseDto.toDomain"){"Error in the Date"}else{dateFormaterCatResponseDto(this.updatedAt)}
    )
}
fun CatEntity.toDomain(): Cat {
    return Cat(
        id = this.id,
        owner = if(this.owner == "null"){ "Without Owner"}else{this.owner},
        tags = if(this.tags.isEmpty()){ listOf("Without Tags")}else{this.tags},
        size = 0,
        mimeType = "Without Type",
        createdAt = if(this.createdAt.isNullOrEmpty()){"Error in the Date"}else{ dateFormaterCatEntity(this.createdAt)},
        updatedAt = if(this.updatedAt.isNullOrEmpty()){"Error in the Date"}else{dateFormaterCatEntity(this.updatedAt)}
    )
}

fun dateFormaterCatEntity(date: String): String {

    try {
    // Parse the input date string as a LocalDateTime "Sun May 01 2022 20:57:11 GMT+0000 (Coordinated Universal Time)"
   //val formatter = DateTimeFormatter.ofPattern("EEE MMM dd yyyy HH:mm:ss 'GMT'Z (zzzz)")
    val formatter = DateTimeFormatter.ofPattern("EEE MMM dd yyyy HH:mm:ss 'GMT'X (zzzz)", Locale.ENGLISH)
    val localDateTime = LocalDateTime.parse(date, formatter)

    // Convert the LocalDateTime to a ZonedDateTime in UTC (opcional)
    val parsedDate = ZonedDateTime.of(localDateTime, ZoneId.of("UTC"))

    // Format the output date string
    val outputFormatter = DateTimeFormatter.ofPattern("EEE, dd MMM yyyy", Locale.ENGLISH)
    return parsedDate.format(outputFormatter)
    } catch (e: Exception) {
        return "Error in the parse date in CatResponseDto.toDomain"
    }


}
fun dateFormaterCatResponseDto(date: String): String {

    try {
    // Parse the input date string as a LocalDateTime
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssX", Locale.ENGLISH)
        val offsetDateTime = OffsetDateTime.parse(date, formatter)

        // Formatear la cadena de fecha de salida
        val outputFormatter = DateTimeFormatter.ofPattern("EEE, dd MMM yyyy", Locale.ENGLISH)
        return offsetDateTime.format(outputFormatter)
    } catch (e: Exception) {
        return "Error in the parse date in CatResponseDto.toDomain"
    }

}