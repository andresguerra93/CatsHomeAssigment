package com.agc.catshomeassignmet.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.agc.catshomeassignmet.data.local.entities.converters.TagsConverter

@Entity(tableName = "cats")
data class CatEntity(
    @PrimaryKey val id: String,
    @ColumnInfo(name = "tags") @TypeConverters(TagsConverter::class) val tags: List<String>,
    @ColumnInfo(name = "owner") val owner: String?,
    @ColumnInfo(name = "createdAt") val createdAt: String,
    @ColumnInfo(name = "updatedAt") val updatedAt: String

)
