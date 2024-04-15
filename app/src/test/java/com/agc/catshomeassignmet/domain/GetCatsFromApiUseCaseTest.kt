package com.agc.catshomeassignmet.domain

import com.agc.catshomeassignmet.data.CatsRepository
import com.agc.catshomeassignmet.domain.model.Cat
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Test

class GetCatsFromApiUseCaseTest {

    @Test
    fun `test Get Cats From Api`() = runBlocking {
        // Given
        val repository = mockk<CatsRepository>(relaxed = true)
        val useCase = GetCatsFromApiUseCase(repository)
        val skip = 10
        val catsFromApi = listOf(
            Cat(
                id = "123",
                owner = "Sample Owner",
                tags = listOf("Tag1", "Tag2"),
                size = 42,
                mimeType = "image/jpeg",
                createdAt = "2024-03-05T18:57:39.550Z",
                updatedAt = "2024-03-05T18:57:39.550Z"
            )

        )

        coEvery { repository.getCatsFromApi(skip) } returns catsFromApi

        // When
        val result = useCase.getCats(skip)

        // Then
        assertEquals(catsFromApi.size, result.size)
        assertEquals(catsFromApi[0].id, result[0].id)
        assertEquals(catsFromApi[0].owner, result[0].owner)
        assertEquals(catsFromApi[0].tags, result[0].tags)
        assertEquals(catsFromApi[0].size, result[0].size)
        assertEquals(catsFromApi[0].mimeType, result[0].mimeType)
        assertEquals(catsFromApi[0].createdAt, result[0].createdAt)
        assertEquals(catsFromApi[0].updatedAt, result[0].updatedAt)

    }
}
