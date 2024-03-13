package com.agc.catshomeassignmet.data.network.dto


import com.google.gson.annotations.SerializedName

data class CatResponseDto(
    @SerializedName("tags") val tags: List<String>,
    @SerializedName("mimetype") val mimeType: String,
    @SerializedName("size") val size: Int,
    @SerializedName("createdAt") val createdAt: String,
    @SerializedName("editedAt") val updatedAt: String,
    @SerializedName("_id") val id: String
)
