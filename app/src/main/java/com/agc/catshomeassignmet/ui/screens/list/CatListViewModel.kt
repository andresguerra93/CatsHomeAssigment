package com.agc.catshomeassignmet.ui.screens.list

import android.content.res.AssetManager
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.agc.catshomeassignmet.domain.GetCatsFromApiUseCase
import com.agc.catshomeassignmet.domain.GetCatsFromRoomUseCase
import com.agc.catshomeassignmet.domain.model.Cat
import com.google.android.material.internal.ContextUtils.getActivity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CatListViewModel @Inject constructor(
    private val getCatsFromRoomUseCase: GetCatsFromRoomUseCase,
    private val getCatsFromApiUseCase: GetCatsFromApiUseCase
) : ViewModel() {

    private var _cats = MutableStateFlow<List<Cat>>(emptyList())
    val cats: StateFlow<List<Cat>> = _cats


    fun loadCatsFromRoom(assets: AssetManager) {
        viewModelScope.launch {

            try {
                _cats.value = getCatsFromRoomUseCase.getCats(assets)

            } catch (e: Exception) {
                // Toast.makeText(getActivity(), "An error on CatlistViewModel with room $e", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun loadMoreCatsFromApi() {
        viewModelScope.launch {

            try {
                _cats.value = cats.value + getCatsFromApiUseCase.getCats(_cats.value.size + 10)
            } catch (e: Exception) {
                // Toast.makeText(getActivity(), "An error on CatlistViewModel with api $e", Toast.LENGTH_SHORT).show()

            }
        }
    }
}
