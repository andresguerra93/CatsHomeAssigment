package com.agc.catshomeassignmet.ui.screens.utils

import org.junit.Assert.*
import org.junit.Test

class BuildImageUrlKtTest{
    @Test
    fun testBuildImageUrl() {
        // Dado
        val baseUrl = "https://example.com/image"
        val type = "thumbnail"
        val width = "100"
        val height = "100"
        val json = "true"
        val imageId = "123"
        val utils = ImageUrlUtils() // Supongamos que tienes una clase ImageUrlUtils que contiene la función buildImageUrl

        // Cuando
        val result = utils.buildImageUrl(baseUrl, type, width, height, json, imageId)

        // Entonces
        // Verifica que el resultado sea el esperado
        // Agrega más verificaciones según tu caso
    }
}