package com.udacity.shoestore.sharedviewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class SharedShoeViewModel : ViewModel() {
    private var _shoeListVO = MutableLiveData<List<Shoe>>(mutableListOf())
    val shoeListVO: LiveData<List<Shoe>>
        get() = _shoeListVO

    private var _eventShowDetailScreen = MutableLiveData(false)
    val eventShowDetailScreen: LiveData<Boolean>
        get() = _eventShowDetailScreen

    private var _eventSaveDetail = MutableLiveData(false)
    val eventSaveDetail: LiveData<Boolean>
        get() = _eventSaveDetail

    private var list = mutableListOf<Shoe>()

    fun fetchShoeList() {
        _shoeListVO.value = list
    }

    override fun onCleared() {
        super.onCleared()
    }

    fun onClickShoeDetail() {
        _eventShowDetailScreen.value = true
    }

    fun onClickShoeDetailComplete() {
        _eventShowDetailScreen.value = false
    }

    fun onClickSubmitForm(inputValues: Shoe) {
        list.add(inputValues)
        _shoeListVO.value = list
        _eventSaveDetail.value = true
    }

    fun onClickSubmitFormCompleted() {
        _eventSaveDetail.value = false
    }

}
