package com.udacity.shoestore.shoelist.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class ShoeListViewModel : ViewModel() {
    private var _shoeList = MutableLiveData<List<Shoe>>(mutableListOf())
    val shoeList: LiveData<List<Shoe>>
        get() = _shoeList

    private var _eventShowDetailScreen = MutableLiveData(false)
    val eventShowDetailScreen: LiveData<Boolean>
        get() = _eventShowDetailScreen

    private val list = listOf(
        Shoe(
            name = "nike",
            size = 2.0,
            company = "asa",
            description = "asadsnzsjksafkjskkhsajhfkjshkjfhkahfkjhakfjhskjfhfaafssa",
            images = listOf()
        ),
        Shoe(
            name = "adidas",
            size = 2.0,
            company = "asa",
            description = "asasgcjyfkfkkjetyresa",
            images = listOf()
        ),
        Shoe(
            name = "zara",
            size = 2.0,
            company = "asa",
            description = "asa2332423535sa",
            images = listOf()
        ),
        Shoe(
            name = "dakota",
            size = 2.0,
            company = "asa",
            description = "asa2332423535sa",
            images = listOf()
        )
    )

    init {
        _shoeList.value = list
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

}