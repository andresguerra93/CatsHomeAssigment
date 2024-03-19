package com.agc.catshomeassignmet.domain

import android.content.res.AssetManager
import com.agc.catshomeassignmet.data.CatsRepository
import com.agc.catshomeassignmet.domain.model.Cat
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Test

class GetCatsFromRoomUseCaseTest {

    @Test
    fun `test Get Cats From Room`() = runBlocking {
        // Given
        val repository = mockk<CatsRepository>(relaxed = true)
        val useCase = GetCatsFromRoomUseCase(repository)
        val assets: AssetManager = mockk() // Replace with an actual AssetManager instance
        val cats = listOf(
            Cat(
                id = "456",
                owner = "Sample Owner",
                tags = listOf("Tag1", "Tag2"),
                size = 42,
                mimeType = "image/jpeg",
                createdAt = "2024-03-12T10:30:00Z",
                updatedAt = "2024-03-12T15:45:00Z"
            )

        )

        coEvery { repository.getAllCatsFromDatabase(assets) } returns cats

        // When
        val result = useCase.getCats(assets)

        // Then
        assert(result.isNotEmpty())


        assertEquals(cats.size, result.size)
        assertEquals(cats[0].id, result[0].id)
        assertEquals(cats[0].tags, result[0].tags)
        assertEquals(cats[0].owner, result[0].owner)
        assertEquals(cats[0].createdAt, result[0].createdAt)
        assertEquals(cats[0].updatedAt, result[0].updatedAt)
    }
}
