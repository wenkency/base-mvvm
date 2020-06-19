package com.lven.baseproject

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class OtherViewModel : ViewModel() {
    var name: MutableLiveData<String> = MutableLiveData("click me")
}