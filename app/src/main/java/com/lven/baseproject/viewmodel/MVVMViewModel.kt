package com.lven.baseproject.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.base.jetpack.BaseMutableLiveData
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MVVMViewModel : ViewModel() {
    var name: BaseMutableLiveData<String> = BaseMutableLiveData("MVVM")
    fun request() {
        viewModelScope.launch {
            delay(2000)
            name.postValue("update")
        }
    }


}