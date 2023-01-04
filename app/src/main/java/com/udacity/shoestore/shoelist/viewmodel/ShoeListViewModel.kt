package com.udacity.shoestore.shoelist.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class ShoeListViewModel : ViewModel() {
    var listVO= MutableLiveData<List<Shoe>>()

    init {
        listVO.value = createShoeList()
    }

    override fun onCleared() {
        super.onCleared()
    }

    private fun createShoeList(): List<Shoe> {
        return listOf(
            Shoe(
                name = "asa",
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