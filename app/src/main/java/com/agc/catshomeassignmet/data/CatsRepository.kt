package com.agc.catshomeassignmet.data


import android.content.res.AssetManager
import com.agc.catshomeassignmet.data.core.mappers.toDomain
import com.agc.catshomeassignmet.data.core.utils.readDataFromJSON
import com.agc.catshomeassignmet.data.local.dao.CatDao
import com.agc.catshomeassignmet.data.local.entities.CatEntity
import com.agc.catshomeassignmet.data.network.CatsApiService
import com.agc.catshomeassignmet.data.network.dto.CatResponseDto
import com.agc.catshomeassignmet.data.network.dto.CatsResponseDto
import com.agc.catshomeassignmet.domain.model.Cat
import javax.inject.Inject

class CatsRepository @Inject constructor(
    private val api: CatsApiService,
    private val catDao: CatDao
) {
    suspend fun getCatsFromApi(skip: Int): List<Cat> {
        val response: List<CatsResponseDto> = api.getCats(skip)
        return response.map { it.toDomain()  }
    }
    suspend fun addRoomAllCatsFromJson(assets: AssetManager):List<CatEntity>{
        val cats = readDataFromJSON(assets) // Implement this method to read data from the JSON file
        catDao.insertCats(cats)
        return cats
    }

    suspend fun getAllCatsFromDatabase(assets: AssetManager):List<Cat>{
        if (catDao.countCats() == 0) {
            val cats = readDataFromJSON(assets) // Implement this method to read data from the JSON file
            catDao.insertCats(cats)
        }
        val response: List<CatEntity> = catDao.getAllCats()
        return response.map { it.toDomain() }
    }

    suspend fun getCatFromApiWithId(id: String): Cat {
        val response: CatResponseDto = api.getCatWithId(id, "true")
        return response.toDomain()
    }

}