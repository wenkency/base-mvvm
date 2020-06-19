package com.lven.baseproject

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    var name: MutableLiveData<String> = MutableLiveData("Main")
}