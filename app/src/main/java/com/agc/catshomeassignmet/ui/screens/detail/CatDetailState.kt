package com.agc.catshomeassignmet.ui.screens.detail

import com.agc.catshomeassignmet.domain.model.Cat

sealed class CatDetailState {
    data object Loading:CatDetailState()
    data class Success(val cat: Cat) : CatDetailState()
    data class Error(val errorMessage: String) : CatDetailState()
}