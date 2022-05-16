package com.lven.base.jetpack

import androidx.collection.ArrayMap

/**
 * 数据传输
 */
object LiveDataBus {

    // 存放订阅者
    private var bus: MutableMap<String, BaseMutableLiveData<Any>> = ArrayMap()


    // 注册订阅者
    fun <T> with(key: String, type: Class<T>, isStick: Boolean = true): BaseMutableLiveData<T> {
        if (!bus.containsKey(key)) {
            bus[key] = BaseMutableLiveData(isStick = isStick)
        }
        return bus[key] as BaseMutableLiveData<T>
    }

    fun <T> postValue(key: String, type: Class<T>, data: T, isStick: Boolean = true) {
        with(key, type, isStick).postValue(data)
    }

    fun remove(key: String) {
        bus.remove(key)
    }

    fun clear() {
        bus.clear()
    }
}