package com.agc.catshomeassignmet.data


import com.agc.catshomeassignmet.data.core.mappers.toDomain
import com.agc.catshomeassignmet.data.core.utils.readDataFromJSON
import com.agc.catshomeassignmet.data.local.dao.CatDao
import com.agc.catshomeassignmet.data.local.entities.CatEntity
import com.agc.catshomeassignmet.data.network.CatsApiService
import com.agc.catshomeassignmet.data.network.dto.CatsResponseDto
import com.agc.catshomeassignmet.domain.model.Cat
import javax.inject.Inject

class CatsRepository @Inject constructor(
    private val api: CatsApiService,
    private val catDao: CatDao
) {
    suspend fun getCatsFromApi(): List<Cat> {
        val response: List<CatsResponseDto> = api.getCats()
        return response.map { it.toDomain()  }
    }

    suspend fun getAllCatsFromDatabase():List<Cat>{
        if (catDao.countCats() == 0) {
            val cats = readDataFromJSON() // Implement this method to read data from the JSON file
            catDao.insertCats(cats)
        }
        val response: List<CatEntity> = catDao.getAllCats()
        return response.map { it.toDomain() }
    }

}