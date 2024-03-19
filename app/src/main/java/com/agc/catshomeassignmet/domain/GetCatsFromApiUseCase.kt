package com.agc.catshomeassignmet.domain

import android.util.Log
import com.agc.catshomeassignmet.data.CatsRepository
import com.agc.catshomeassignmet.domain.model.Cat
import javax.inject.Inject

class GetCatsFromApiUseCase @Inject constructor(private val repository: CatsRepository) {
    suspend fun getCats(skip: Int): List<Cat> {
        val catsFromApi = repository.getCatsFromApi(skip)
    //    Log.d("MiTag", "The cats from API in the use cas are: $catsFromApi")
        //return repository.getAllCatsFromDatabase()
        return catsFromApi
    }

}