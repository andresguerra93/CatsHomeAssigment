package com.agc.catshomeassignmet.ui.screens.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.agc.catshomeassignmet.domain.GetCatFromApiWithIdUseCase
import com.agc.catshomeassignmet.domain.GetCatsFromApiUseCase
import com.agc.catshomeassignmet.domain.model.Cat
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class CatDetailViewModel @Inject constructor(
    private val getCatFromApiWithIdUseCase: GetCatFromApiWithIdUseCase
) : ViewModel() {


    private var _state = MutableStateFlow<CatDetailState>(CatDetailState.Loading)
    val state: StateFlow<CatDetailState> = _state

    fun getCatDetail(catId: String) {

        viewModelScope.launch {
            _state.value = CatDetailState.Loading
            val cat = withContext(Dispatchers.IO) { getCatFromApiWithIdUseCase.getCatFromApiWithId(catId) }
            if(cat!=null){
                _state.value = CatDetailState.Success(cat)
            }else{
                _state.value = CatDetailState.Error("Error loading cat from API with Id")
            }
        }
    }
    }
