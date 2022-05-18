package com.lven.baseproject.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    var name: MutableLiveData<String> = MutableLiveData("Main")

    /**
     * 模拟网络请求
     */
    fun request() {
        viewModelScope.launch(Dispatchers.IO) {
            delay(2000)
            name.postValue("${Thread.currentThread().name}:update")
        }
    }
}