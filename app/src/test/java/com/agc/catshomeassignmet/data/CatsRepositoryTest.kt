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
import org.junit.Assert.*
import org.junit.Test

class CatsRepositoryTest{

    @Test
    fun testGetCatsFromApi() = runBlocking {
        // Dado
        val apiService = mockk<CatsApiService>()
        val catDao = mockk<CatDao>()
        val repository = CatsRepository(apiService, catDao)
        val skip = 10
        val catsResponse = listOf(CatsResponseDto(/* Datos de ejemplo */))

        // Cuando
        coEvery { apiService.getCats(skip) } returns catsResponse

        // Entonces
        val result = repository.getCatsFromApi(skip)
        assert(result.isNotEmpty())
        // Agrega más verificaciones según tu caso
    }

    @Test
    fun testGetAllCatsFromDatabase() = runBlocking {
        // Dado
        val apiService = mockk<CatsApiService>()
        val catDao = mockk<CatDao>()
        val repository = CatsRepository(apiService, catDao)
        val assets: AssetManager = /* Obtén una instancia de AssetManager */
        val catsEntity = listOf(CatEntity(/* Datos de ejemplo */))

        // Cuando
        coEvery { catDao.countCats() } returns 0
        coEvery { catDao.getAllCats() } returns catsEntity

        // Entonces
        val result = repository.getAllCatsFromDatabase(assets)
        assert(result.isNotEmpty())
        // Agrega más verificaciones según tu caso
    }

    @Test
    fun testGetCatFromApiWithId() = runBlocking {
        // Dado
        val apiService = mockk<CatsApiService>()
        val catDao = mockk<CatDao>()
        val repository = CatsRepository(apiService, catDao)
        val catId = "123"
        val catResponse = CatResponseDto(/* Datos de ejemplo */)

        // Cuando
        coEvery { apiService.getCatWithId(catId, "true") } returns catResponse

        // Entonces
        val result = repository.getCatFromApiWithId(catId)
        // Verifica que el resultado sea el esperado
        // Agrega más verificaciones según tu caso
    }

}