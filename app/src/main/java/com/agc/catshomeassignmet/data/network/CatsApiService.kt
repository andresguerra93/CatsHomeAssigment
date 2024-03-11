package com.agc.catshomeassignmet.data.network

import com.agc.catshomeassignmet.data.network.dto.CatsResponseDto
import retrofit2.http.GET

interface CatsApiService {
    @GET("/api/cats")
    suspend fun getCats(): List<CatsResponseDto>
}
