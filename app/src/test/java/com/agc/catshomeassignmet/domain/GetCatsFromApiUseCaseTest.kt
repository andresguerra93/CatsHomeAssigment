package com.agc.catshomeassignmet.domain

import com.agc.catshomeassignmet.data.CatsRepository
import com.agc.catshomeassignmet.domain.model.Cat
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Test

class GetCatsFromApiUseCaseTest{
    @Test
    fun testGetCatsFromApi() = runBlocking {
        // Dado
        val repository = mockk<CatsRepository>()
        val useCase = GetCatsFromApiUseCase(repository)
        val skip = 10
        val catsFromApi = listOf(Cat(/* Datos de ejemplo */))

        coEvery { repository.getCatsFromApi(skip) } returns catsFromApi

        // Cuando
        val result = useCase.getCats(skip)

        // Entonces
        // Verifica que el resultado sea el esperado
        // Agrega más verificaciones según tu caso
    }
}