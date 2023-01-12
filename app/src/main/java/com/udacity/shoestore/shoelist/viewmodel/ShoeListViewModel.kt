package com.udacity.shoestore.shoelist.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class ShoeListViewModel : ViewModel() {
    private var inputValues = Shoe("", 0.0, "", "")
    private var _shoeListVO = MutableLiveData<List<Shoe>>(mutableListOf())
    val shoeListVO: LiveData<List<Shoe>>
        get() = _shoeListVO

    private var _eventShowDetailScreen = MutableLiveData(false)
    val eventShowDetailScreen: LiveData<Boolean>
        get() = _eventShowDetailScreen

    private var _stateShowErrorMessage = MutableLiveData<Boolean>()
    val stateShowErrorMessage: LiveData<Boolean>
        get() = _stateShowErrorMessage

    private var list = mutableListOf<Shoe>()

    fun fetchShoeList() {
        _shoeListVO.value = list
    }

    override fun onCleared() {
        super.onCleared()
    }

    fun onClickAddShoeDetail() {
        _eventShowDetailScreen.value = true
    }

    fun onClickAddShoeDetailComplete() {
        _eventShowDetailScreen.value = false
    }

    fun onClickSaveDetail() {
        list.add(inputValues)
        _shoeListVO.value = list
    }

    fun onUserChangedInput(inputValues: Shoe) {
        if (inputValues.name.isBlank() || inputValues.description.isBlank() || inputValues.company.isBlank()) {
            _stateShowErrorMessage.value = true
        } else {
            this.inputValues = inputValues
        }

    }

}