package com.agc.catshomeassignmet.data

import android.content.res.AssetManager
import com.agc.catshomeassignmet.data.local.dao.CatDao
import com.agc.catshomeassignmet.data.local.entities.CatEntity
import com.agc.catshomeassignmet.data.network.CatsApiService
import com.agc.catshomeassignmet.data.network.dto.CatResponseDto
import com.agc.catshomeassignmet.data.network.dto.CatsResponseDto
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Test

class CatsRepositoryTest{

    @Test
    fun `test Get Cats From Api`() = runBlocking {
        // Given
        val apiService = mockk<CatsApiService>(relaxed = true)
        val catDao = mockk<CatDao>(relaxed = true)
        val repository = CatsRepository(apiService, catDao)
        val skip = 10
        val catsResponse = listOf(CatsResponseDto(
            id = "123",
            tags = listOf("Tag1", "Tag2"),
            size = 42,
            mimeType = "image/jpeg"
        ))

        // When
        coEvery { apiService.getCats(skip) } returns catsResponse

        // Then
        val result = repository.getCatsFromApi(skip)
        assert(result.isNotEmpty())

    }

    @Test
    fun `test Get All Cats From Database`() =runBlocking {
        // Given
        val apiService = mockk<CatsApiService>(relaxed = true)
        val catDao = mockk<CatDao>(relaxed = true)
        val repository = CatsRepository(apiService, catDao)


        val assets = mockk<AssetManager>(relaxed = true)

        val catsEntity = listOf(CatEntity(
            id = "789",
            owner = "null",
            tags = emptyList(),
            createdAt = "null",
            updatedAt = "2024-03-12T15:45:00Z"

        ))

        // When
        coEvery { catDao.countCats() } returns 0
        coEvery { catDao.getAllCats() } returns catsEntity

        // Then
        val result = repository.getAllCatsFromDatabase(assets)
        assert(result.isNotEmpty())

    }
    @Test
    fun `test Get Cat From Api With Id`() = runBlocking {
        // Given
        val apiService = mockk<CatsApiService>(relaxed = true)
        val catDao = mockk<CatDao>(relaxed = true)
        val repository = CatsRepository(apiService, catDao)
        val catId = "123"
        val catResponse = CatResponseDto(
            id = "456",
            tags = emptyList(),
            size = 0,
            mimeType = "null",
            createdAt = "2024-03-12T10:30:00Z",
            updatedAt = "2024-03-12T10:30:00Z"

        )

        // When
        coEvery { apiService.getCatWithId(catId, "true") } returns catResponse

        // Then
        val result = repository.getCatFromApiWithId(catId)

    }

}