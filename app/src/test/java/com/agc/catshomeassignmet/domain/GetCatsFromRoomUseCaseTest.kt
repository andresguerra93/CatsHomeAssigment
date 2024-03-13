package com.agc.catshomeassignmet.domain

import android.content.res.AssetManager
import com.agc.catshomeassignmet.data.CatsRepository
import com.agc.catshomeassignmet.data.local.entities.CatEntity
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Test

class GetCatsFromRoomUseCaseTest{
    @Test
    fun testGetCatsFromRoom() = runBlocking {
        // Dado
        val repository = mockk<CatsRepository>()
        val useCase = GetCatsFromRoomUseCase(repository)
        val assets: AssetManager = /* Obtén una instancia de AssetManager */
        val catsEntity = listOf(CatEntity(/* Datos de ejemplo */))

        coEvery { repository.getAllCatsFromDatabase(assets) } returns catsEntity

        // Cuando
        val result = useCase.getCats(assets)

        // Entonces
        // Verifica que el resultado no sea nulo y tenga la estructura esperada
        assert(result.isNotEmpty())
        // Agrega más verificaciones según tu caso
    }
}