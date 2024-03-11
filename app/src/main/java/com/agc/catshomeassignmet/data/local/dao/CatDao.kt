package com.agc.catshomeassignmet.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.agc.catshomeassignmet.data.local.entities.CatEntity

@Dao
interface CatDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCats(cats: List<CatEntity>)

    @Query("SELECT * FROM cats")
    suspend fun getAllCats(): List<CatEntity>

    @Query("SELECT COUNT(*) FROM cats")
    suspend fun countCats(): Int
}