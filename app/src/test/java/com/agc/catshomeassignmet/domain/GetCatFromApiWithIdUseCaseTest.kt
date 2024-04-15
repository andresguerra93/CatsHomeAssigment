package com.agc.catshomeassignmet.domain

import com.agc.catshomeassignmet.data.CatsRepository
import com.agc.catshomeassignmet.domain.model.Cat
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Test

class GetCatFromApiWithIdUseCaseTest {

    @Test
    fun `test Get Cat From Api With Id`() = runBlocking {
        // Given
        val repository = mockk<CatsRepository>(relaxed = true)
        val useCase = GetCatFromApiWithIdUseCase(repository)
        val catId = "123"
        val cat = Cat(
            id = "456",
            owner = "Sample Owner",
            tags = listOf("Tag1", "Tag2"),
            size = 42,
            mimeType = "image/jpeg",
            createdAt = "2022-06-01T22:29:22.394Z",
            updatedAt = "2022-06-01T22:29:22.394Z"
        )

        coEvery { repository.getCatFromApiWithId(catId) } returns cat

        // When
        val result = useCase.getCatFromApiWithId(catId)

        // Then
        assertEquals(cat.id, result.id)
        assertEquals(cat.owner, result.owner)
        assertEquals(cat.tags, result.tags)
        assertEquals(cat.size, result.size)
        assertEquals(cat.mimeType, result.mimeType)
        assertEquals(cat.createdAt, result.createdAt)
        assertEquals(cat.updatedAt, result.updatedAt)

    }
}