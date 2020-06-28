package com.lven.baseproject

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class FragmentViewModel : ViewModel() {
    var position: MutableLiveData<Int> = MutableLiveData(0)

}