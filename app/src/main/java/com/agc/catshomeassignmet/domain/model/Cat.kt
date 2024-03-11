package com.agc.catshomeassignmet.domain.model

data class Cat(
    val id: String,
    val owner: String?,
    val tags: List<String>,
    val size: Int,
    val mimeType: String,
    val createdAt: String,
    val updatedAt: String
)
