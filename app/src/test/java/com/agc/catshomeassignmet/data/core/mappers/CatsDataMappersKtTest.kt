package com.agc.catshomeassignmet.data.core.mappers

import com.agc.catshomeassignmet.data.local.entities.CatEntity
import com.agc.catshomeassignmet.data.network.dto.CatResponseDto
import com.agc.catshomeassignmet.data.network.dto.CatsResponseDto
import junit.framework.TestCase.assertEquals
import org.junit.Test

class CatsDataMappersKtTest {
    @Test
    fun `test Cats ResponseDto ToDomain`() {
        // GIVEN
        val catsResponseDto = CatsResponseDto(
            id = "123",
            tags = listOf("Tag1", "Tag2"),
            size = 42,
            mimeType = "image/jpeg"
        )

        // WHEN
        val cat = catsResponseDto.toDomain()

        // THEN
        assert(cat.id == "123")
        assert(cat.owner == "Without Owner")
        assert(cat.tags == listOf("Tag1", "Tag2"))
        assert(cat.size == 42)
        assert(cat.mimeType == "image/jpeg")
        assert(cat.createdAt == "Without Date Of Creation")
        assert(cat.updatedAt == "Without Date Of Update")
    }

    @Test
    fun `test Cat ResponseDto ToDomain`() {
        // GIVEN
        val catResponseDto = CatResponseDto(
            id = "456",
            tags = emptyList(),
            size = 0,
            mimeType = "",
            createdAt = "",
            updatedAt = "2024-03-12T10:30:00Z"

        )

        // WHEN
        val cat = catResponseDto.toDomain()

        // THEN
        assert(cat.id == "456")
        assert(cat.owner == "Without Owner")
        assert(cat.tags == listOf("Without Tags"))
        assert(cat.size == 0)
        assert(cat.mimeType == "Without mimeType")
        assert(cat.createdAt == "Error in the Date")
        //assert( == "Sun, 12 Mar 2024")
        assertEquals("Tue, 12 Mar 2024", cat.updatedAt)
    }
    @Test
    fun `test Cat Entity To Domain`() {
        // GIVEN
        val catEntity = CatEntity(
            id = "789",
            owner = "null",
            tags = emptyList(),
            createdAt = "",
            updatedAt = "Tue Mar 15 2022 14:30:00 GMT+0000 (Coordinated Universal Time)"

        )

        // WHEN
        val cat = catEntity.toDomain()

        // THEN
        assert(cat.id == "789")
        assert(cat.owner == "Without Owner")
        assert(cat.tags == listOf("Without Tags"))
        assert(cat.size == 0)
        assert(cat.mimeType == "Without Type")
        assert(cat.createdAt == "Error in the Date")
        assert(cat.updatedAt == "Tue, 15 Mar 2022")
    }

    @Test
    fun `test Date Formatter for Cat Entity`() {
        // GIVEN
        val date = "Tue Mar 15 2022 14:30:00 GMT+0000 (Coordinated Universal Time)"

        // WHEN
        val formattedDate = dateFormaterCatEntity(date)

        // THEN
        assert(formattedDate == "Tue, 15 Mar 2022")
    }

    @Test
    fun `test Date Formatter for Cat ResponseDto`() {
        // GIVEN
        val date = "2024-03-12T10:30:00Z"

        // WHEN
        val formattedDate = dateFormaterCatResponseDto(date)

        // THEN
        assertEquals("Tue, 12 Mar 2024", formattedDate)
    }

}
