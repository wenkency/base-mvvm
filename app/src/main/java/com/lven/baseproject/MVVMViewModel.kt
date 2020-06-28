package com.lven.baseproject

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MVVMViewModel : ViewModel() {
    var name: MutableLiveData<String> = MutableLiveData("MVVM")
    fun request() {
        viewModelScope.launch {
            delay(2000)
            name.postValue("update")
        }
    }
}