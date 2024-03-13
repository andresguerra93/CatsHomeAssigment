package com.agc.catshomeassignmet.data.core.mappers

import com.agc.catshomeassignmet.data.local.entities.CatEntity
import com.agc.catshomeassignmet.data.network.dto.CatResponseDto
import com.agc.catshomeassignmet.data.network.dto.CatsResponseDto
import org.junit.Assert.*
import org.junit.Test

class CatsDataMappersKtTest {
    @Test
    fun `test Cats ResponseDto ToDomain`() {
        // GIVEN*
        val catsResponseDto = CatsResponseDto(
            id = 123,
            tags = listOf("Tag1", "Tag2"),
            size = 42,
            mimeType = "image/jpeg"

        )

        // WHEN
        val cat = catsResponseDto.toDomain()

        // THEN
        assert(cat.id == 123)
        assert(cat.owner == "Without Owner")
        assert(cat.tags == listOf("Tag1", "Tag2"))
        assert(cat.size == 42)
        assert(cat.mimeType == "image/jpeg")
        assert(cat.createdAt == "Without Date Of Creation")
        assert(cat.updatedAt == "Without Date Of Update")
    }

    @Test
    fun testCatResponseDtoToDomain() {
        // Dado
        val catResponseDto = CatResponseDto(
            id = 456,
            tags = null,
            size = 0,
            mimeType = null,
            createdAt = null,
            updatedAt = "2024-03-12T10:30:00Z"
            // Agrega otros campos según tu caso
        )

        // Cuando
        val cat = catResponseDto.toDomain()

        // Entonces
        assert(cat.id == 456)
        assert(cat.owner == "Without Owner")
        assert(cat.tags == listOf("Without Tags"))
        assert(cat.size == 0)
        assert(cat.mimeType == "Without mimeType")
        assert(cat.createdAt == "Error in the Date")
        assert(cat.updatedAt == "2024-03-12T10:30:00Z")
    }
    @Test
    fun testCatEntityToDomain() {
        // Dado
        val catEntity = CatEntity(
            id = 789,
            owner = "null",
            tags = emptyList(),
            createdAt = null,
            updatedAt = "2024-03-12T15:45:00Z"
            // Agrega otros campos según tu caso
        )

        // Cuando
        val cat = catEntity.toDomain()

        // Entonces
        assert(cat.id == 789)
        assert(cat.owner == "Without Owner")
        assert(cat.tags == listOf("Without Tags"))
        assert(cat.size == 0)
        assert(cat.mimeType == "Without Type")
        assert(cat.createdAt == "Error in the Date")
        assert(cat.updatedAt == "2024-03-12T15:45:00Z")
    }


    @Test
    fun testDateFormatterCatEntity() {
        // Dado
        val date = "Thu Mar 10 2022 10:30:00 GMT-0800 (Pacific Standard Time)"

        // Cuando
        val formattedDate = dateFormaterCatEntity(date)

        // Entonces
        assert(formattedDate == "Thu, 10 Mar 2022")
    }


    @Test
    fun testDateFormatterCatEntity() {
        // Dado
        val date = "Thu Mar 10 2022 10:30:00 GMT-0800 (Pacific Standard Time)"

        // Cuando
        val formattedDate = dateFormaterCatEntity(date)

        // Entonces
        assert(formattedDate == "Thu, 10 Mar 2022")
    }

}
