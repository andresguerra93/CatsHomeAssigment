package com.agc.catshomeassignmet.ui.screens.utils

fun builImageUrl(
    baseUrl: String,
    type: String? = null,
    filter: String? = null,
    fit: String? = null,
    position: String? = null,
    width: String? = null,
    height: String? = null,
    blur: String? = null,
    r: String? = null,
    g: String? = null,
    b: String? = null,
    brightness: String? = null,
    saturation: String? = null,
    hue: String? = null,
    lightness: String? = null,
    json: String = "false",
    id: String? = null
): String {
    val queryParams = mutableListOf<String>()
    type?.let { queryParams.add("type=$it") }
    filter?.let { queryParams.add("filter=$it") }
    fit?.let { queryParams.add("fit=$it") }
    position?.let { queryParams.add("position=$it") }
    width?.let { queryParams.add("width=$it") }
    height?.let { queryParams.add("height=$it") }
    blur?.let { queryParams.add("blur=$it") }
    r?.let { queryParams.add("r=$it") }
    g?.let { queryParams.add("g=$it") }
    b?.let { queryParams.add("b=$it") }
    brightness?.let { queryParams.add("brightness=$it") }
    saturation?.let { queryParams.add("saturation=$it") }
    hue?.let { queryParams.add("hue=$it") }
    lightness?.let { queryParams.add("lightness=$it") }
    queryParams.add("json=$json")


    val queryString = queryParams.joinToString("&")
    return if (queryString.isNotEmpty()) {
        "$baseUrl$id?$queryString"
    } else {
        baseUrl
    }
}