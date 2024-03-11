package com.agc.catshomeassignmet.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.agc.catshomeassignmet.data.local.dao.CatDao
import com.agc.catshomeassignmet.data.local.entities.CatEntity
import com.agc.catshomeassignmet.data.local.entities.converters.TagsConverter

@Database(entities = [CatEntity::class], version = 1, exportSchema = true)
@TypeConverters(TagsConverter::class)
abstract class CatsDataBase: RoomDatabase() {
    abstract fun catDao(): CatDao
}