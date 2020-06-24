package com.lven.baseproject

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MVVMViewModel : ViewModel() {
    var name: MutableLiveData<String> = MutableLiveData("MVVM")

}