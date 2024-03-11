package com.agc.catshomeassignmet.data.network.dto

import com.google.gson.annotations.SerializedName

data class CatsResponseDto(
    @SerializedName("_id") val id: String,
    @SerializedName("mimetype") val mimeType: String,
    @SerializedName("size") val size: Int,
    @SerializedName("tags") val tags: List<String>
)
