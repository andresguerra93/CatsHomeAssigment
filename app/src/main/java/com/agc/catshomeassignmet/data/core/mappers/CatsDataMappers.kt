package com.agc.catshomeassignmet.data.core.mappers


import com.agc.catshomeassignmet.data.local.entities.CatEntity
import com.agc.catshomeassignmet.data.network.dto.CatResponseDto
import com.agc.catshomeassignmet.data.network.dto.CatsResponseDto
import com.agc.catshomeassignmet.domain.model.Cat


fun CatsResponseDto.toDomain(): Cat {
    return Cat(
        id = this.id,
        owner = "Without Owner",
        tags = this.tags.ifEmpty { arrayListOf("Without Tags") },
        size = this.size ?: 0,
        mimeType = this.mimeType ?: "Without mimeType",
        createdAt = "Without Date Of Creation",
        updatedAt = "Without Date Of Update",
    )
}

fun CatResponseDto.toDomain(): Cat {
    return Cat(
        id = this.id,
        owner = "Without Owner",
        tags = if (this.tags.isNullOrEmpty()) {
            arrayListOf("Without Tags")
        } else {
            this.tags
        },
        size = this.size ?: 0,
        mimeType = if (this.mimeType.isNullOrEmpty()) {
            "Without mimeType"
        } else {
            this.mimeType
        },
        createdAt = if (this.createdAt.isNullOrEmpty() || this.createdAt == "Error in the parse date in CatResponseDto.toDomain") {
            "Error in the Date"
        } else {
            dateFormaterCatResponseDto(this.createdAt)
        },
        updatedAt = if (this.updatedAt.isNullOrEmpty() || this.createdAt == "Error in the parse date in CatResponseDto.toDomain") {
            "Error in the Date"
        } else {
            dateFormaterCatResponseDto(this.updatedAt)
        }
    )
}

fun CatEntity.toDomain(): Cat {
    return Cat(
        id = this.id,
        owner = if (this.owner == "null") {
            "Without Owner"
        } else {
            this.owner
        },
        tags = if (this.tags.isEmpty()) {
            listOf("Without Tags")
        } else {
            this.tags
        },
        size = 0,
        mimeType = "Without Type",
        createdAt = if (this.createdAt.isNullOrEmpty()) {
            "Error in the Date"
        } else {
            dateFormaterCatEntity(this.createdAt)
        },
        updatedAt = if (this.updatedAt.isNullOrEmpty()) {
            "Error in the Date"
        } else {
            dateFormaterCatEntity(this.updatedAt)
        }
    )
}

fun dateFormaterCatEntity(date: String): String {

    try {
        //Tue Oct 11 2022 07:52:32 GMT+0000 (Coordinated Universal Time)
        val parts = date.split(" ")

        val monthMap = hashMapOf(
            "Jan" to 1,
            "Feb" to 2,
            "Mar" to 3,
            "Apr" to 4,
            "May" to 5,
            "Jun" to 6,
            "Jul" to 7,
            "Aug" to 8,
            "Sep" to 9,
            "Oct" to 10,
            "Nov" to 11,
            "Dec" to 12
        )
        val month = parts[1]
        val nombreMonthString = monthMap[month].toString().padStart(2, '0')
        val dayOfMonth = parts[2]
        val year = parts[3]

        // Log.d("MiTag", "La fecha quedo: $dayOfMonth/$nombreMonthString/$year")
        // Formatear la fecha en el formato deseado
        return """$dayOfMonth/$nombreMonthString/$year"""

    } catch (e: Exception) {
        return "Error in the parse date in CatEntity.toDomain"
    }


}

fun dateFormaterCatResponseDto(date: String): String {

    try {
        val parts = date.split("-")

        val year = parts[0]
        val month = parts[1]
        val endDate = parts[2].split("T")
        val dayOfMonth = endDate[0]

        return "$dayOfMonth/$month/$year"
    } catch (e: Exception) {
        return "Error in the parse date in CatResponseDto.toDomain"
    }

}