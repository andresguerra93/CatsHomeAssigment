package com.agc.catshomeassignmet.data.core.utils

import android.content.res.AssetManager
import com.agc.catshomeassignmet.data.CatsRepository
import com.agc.catshomeassignmet.data.local.dao.CatDao
import com.agc.catshomeassignmet.data.network.CatsApiService
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import java.io.InputStream

class ReadDataFromJsonKtTest{


    @Test
    fun `test readDataFromJSON`() = runBlocking {
        // Given
        val assets = mockk<AssetManager>(relaxed = true)
        val apiService = mockk<CatsApiService>(relaxed = true)
        val catDao = mockk<CatDao>(relaxed = true)
        val repository = CatsRepository(apiService, catDao)
        val inputStream = mockk<InputStream>(relaxed = true)
        val inputString = """[
            {
                "_id": "MfHpIfFxibiqr8Wh",
                "mimetype": "image/jpeg",
                "size": 64591,
                "tags": ["siamese", "meditating", "sleepy", "cute", "outside"]
            },
            {
                "_id": "1DrcyohjhwcNaRIz",
                "mimetype": "image/jpeg",
                "size": 12015,
                "tags": ["cute", "orange", "sleepy", "tired", "outside", "grass"]
            }
            // Add more sample data as needed
        ]"""


        coEvery { assets.open("cats.json") } returns inputStream
        coEvery { inputStream.bufferedReader().use { it.readText() } } returns inputString

        // When
        val result = repository.getAllCatsFromDatabase(assets)

        // Then
        assertEquals(2, result.size) //
        assertEquals("MfHpIfFxibiqr8Wh", result[0].id)
        assertEquals(listOf("siamese", "meditating", "sleepy", "cute", "outside"), result[0].tags)
    }
}