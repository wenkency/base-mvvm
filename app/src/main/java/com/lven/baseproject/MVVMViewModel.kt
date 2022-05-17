package com.lven.baseproject

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lven.base.jetpack.BaseMutableLiveData
import com.lven.retrofit.RetrofitPresenter
import com.lven.retrofit.callback.BeanCallback
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