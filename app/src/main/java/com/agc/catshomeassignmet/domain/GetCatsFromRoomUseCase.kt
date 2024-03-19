package com.agc.catshomeassignmet.domain

import android.content.res.AssetManager
import android.util.Log
import com.agc.catshomeassignmet.data.CatsRepository
import com.agc.catshomeassignmet.domain.model.Cat
import javax.inject.Inject

class GetCatsFromRoomUseCase @Inject constructor(private val repository: CatsRepository) {
    suspend fun getCats(assets: AssetManager): List<Cat> {
        val catsFromRoom = repository.getAllCatsFromDatabase(assets)
      //  Log.d("MyTag", "The cats from ROOM in the use cas are: $catsFromRoom")
        //return repository.getAllCatsFromDatabase()
        return catsFromRoom
    }

}