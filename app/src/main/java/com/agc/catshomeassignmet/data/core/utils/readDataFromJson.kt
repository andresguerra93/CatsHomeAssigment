package com.agc.catshomeassignmet.data.core.utils

import android.content.res.AssetManager
import com.agc.catshomeassignmet.data.local.entities.CatEntity
import com.google.gson.Gson
import org.json.JSONArray
import java.io.InputStream

fun readDataFromJSON(assets: AssetManager): List<CatEntity> {
    try {
        val inputStream: InputStream = assets.open("cats.json")
        val inputString = inputStream.bufferedReader().use { it.readText() }
        val jsonArray = JSONArray(inputString)

        val catEntities = mutableListOf<CatEntity>()
        for (i in 0 until jsonArray.length()) {
            val jsonObject = jsonArray.getJSONObject(i)
            val id = jsonObject.getString("_id")
            val tags = Gson().fromJson(jsonObject.getJSONArray("tags").toString(), List::class.java) as List<String>?:arrayListOf("Without Tags")
            val owner = jsonObject.optString("owner", "Dont have Owner")
            val createdAt = jsonObject.getString("createdAt")
            val updatedAt = jsonObject.getString("updatedAt")

            catEntities.add(CatEntity(id, tags, owner, createdAt, updatedAt))
        }

        return catEntities
    } catch (e: Exception) {
        println("Error: ${e.message}")
        return emptyList()
    }
}