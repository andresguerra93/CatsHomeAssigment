package com.agc.catshomeassignmet.domain

import com.agc.catshomeassignmet.data.CatsRepository
import com.agc.catshomeassignmet.domain.model.Cat
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Test

class GetCatFromApiWithIdUseCaseTest{
    @Test
    fun testGetCatFromApiWithId() = runBlocking {
        // Dado
        val repository = mockk<CatsRepository>()
        val useCase = GetCatFromApiWithIdUseCase(repository)
        val catId = "123"
        val cat = Cat(/* Datos de ejemplo */)

        coEvery { repository.getCatFromApiWithId(catId) } returns cat

        // Cuando
        val result = useCase.getCatFromApiWithId(catId)

        // Entonces
        // Verifica que el resultado sea el esperado
        // Agrega más verificaciones según tu caso
    }
}