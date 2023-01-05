package com.udacity.shoestore.shoelist.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class ShoeListViewModel : ViewModel() {
    private var _showList = MutableLiveData<List<Shoe>>()
    val showList: LiveData<List<Shoe>>
        get() = _showList

    init {
        _showList.value = createShoeList()
    }

    override fun onCleared() {
        super.onCleared()
    }

    private fun createShoeList(): List<Shoe> {
        return listOf(
            Shoe(
                name = "asdfa",
                size = 2.0,
                company = "asa",
                description = "asasa",
                images = listOf()
            ),
            Shoe(
                name = "adfdsgdfsa",
                size = 2.0,
                company = "asa",
                description = "asasa",
                images = listOf()
            ),
            Shoe(
                name = "asa",
                size = 2.0,
                company = "asa",
                description = "asasa",
                images = listOf()
            )
        )
    }
}