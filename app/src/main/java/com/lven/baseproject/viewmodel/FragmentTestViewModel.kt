package com.lven.baseproject.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class FragmentTestViewModel : ViewModel() {
    var name = MutableLiveData<String>()
}