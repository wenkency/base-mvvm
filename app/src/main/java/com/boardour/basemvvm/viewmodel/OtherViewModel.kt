package com.boardour.basemvvm.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

class OtherViewModel(application: Application) : AndroidViewModel(application) {
    var name: MutableLiveData<String> = MutableLiveData("click me")
}