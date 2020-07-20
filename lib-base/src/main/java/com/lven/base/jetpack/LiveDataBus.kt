package com.lven.base.jetpack

import androidx.collection.ArrayMap
import androidx.lifecycle.MutableLiveData


class LiveDataBus private constructor() {

    // 存放订阅者
    private var bus: MutableMap<String, MutableLiveData<Any>> = ArrayMap()

    companion object {
        val instance by lazy {
            LiveDataBus()
        }
    }

    // 注册订阅者
    fun <T> with(key: String, type: Class<T>): MutableLiveData<T> {
        if (!bus.containsKey(key)) {
            bus[key] = MutableLiveData()
        }
        return bus[key] as MutableLiveData<T>
    }

    fun <T> post(key: String, type: Class<T>, data: T) {
        with(key, type).postValue(data)
    }
}