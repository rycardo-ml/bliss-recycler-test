package com.example.recyclerview.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.*

class HomeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text

    private val _names = MutableLiveData<List<String>>()
    val names: LiveData<List<String>> = _names

    init {
        val tempNames = mutableListOf<String>()
        for (i in 1..100) {
            tempNames.add("$i - ${UUID.randomUUID()}")
        }

        _names.postValue(tempNames)
    }
}