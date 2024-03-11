package com.agc.catshomeassignmet.data.core.utils

import com.agc.catshomeassignmet.data.local.entities.CatEntity
import com.google.gson.Gson
import org.json.JSONArray

fun readDataFromJSON(): List<CatEntity> {
    val jsonString = object {}.javaClass.classLoader?.getResourceAsStream("cats.json")?.bufferedReader()?.use { it.readText() }
    val jsonArray = JSONArray(jsonString)

    val catEntities = mutableListOf<CatEntity>()
    for (i in 0 until jsonArray.length()) {
        val jsonObject = jsonArray.getJSONObject(i)
        val id = jsonObject.getString("id")
        val tags = Gson().fromJson(jsonObject.getJSONArray("tags").toString(), List::class.java) as List<String>
        val owner = jsonObject.optString("owner","Dont have Owner")
        val createdAt = jsonObject.getString("createdAt")
        val updatedAt = jsonObject.getString("updatedAt")

        catEntities.add(CatEntity(id, tags, owner, createdAt, updatedAt))
    }

    return catEntities
}