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

    fun onClickSubmitForm(inputValues: Shoe) {
        list.add(inputValues)
        _shoeListVO.value = list
    }

}
