package com.agc.catshomeassignmet.data.network

import com.agc.catshomeassignmet.data.network.dto.CatResponseDto
import com.agc.catshomeassignmet.data.network.dto.CatsResponseDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CatsApiService {
    @GET("/api/cats")
    suspend fun getCats(@Query("skip") skip: Int): List<CatsResponseDto>
    @GET("/cat/{id}")
    suspend fun getCatWithId(@Path("id")id: String, @Query("json") json: String): CatResponseDto
}
