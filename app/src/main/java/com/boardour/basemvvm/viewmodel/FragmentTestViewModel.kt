package com.boardour.basemvvm.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class FragmentTestViewModel : ViewModel() {
    var name = MutableLiveData<String>()
}