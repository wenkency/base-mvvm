package com.lven.baseproject.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.lven.baseproject.BaseApplication

class OtherViewModel(application: Application) : AndroidViewModel(application) {
    var name: MutableLiveData<String> = MutableLiveData("click me")
    var context = application
    fun print() {
        Log.e("TAG", "${context}")
        Log.e("TAG", "${getApplication<BaseApplication>()}")
    }
}