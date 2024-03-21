package com.agc.catshomeassignmet.domain

import android.util.Log
import com.agc.catshomeassignmet.data.CatsRepository
import com.agc.catshomeassignmet.domain.model.Cat
import javax.inject.Inject

class GetCatFromApiWithIdUseCase @Inject constructor(private val repository: CatsRepository){
    suspend fun getCatFromApiWithId(id: String): Cat {
        val catFromApiId = repository.getCatFromApiWithId(id)
        Log.d("MiTag", "The cat from API Id in the use case are: $catFromApiId ")
        //return repository.getAllCatsFromDatabase()
        return catFromApiId
    }

}