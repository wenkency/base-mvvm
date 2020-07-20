package com.lven.base.jetpack

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import java.lang.reflect.Field
import java.lang.reflect.Method

/**
 * 第一次订阅不会加载
 */
class BaseMutableLiveData<T> : MutableLiveData<T>() {
    override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {
        super.observe(owner, observer)
        hook(observer)
    }

    private fun hook(observer: Observer<in T>) {
        try {
            //1.得到mLastVersion
            val liveDataClass = LiveData::class.java
            val mObserversFeild: Field = liveDataClass.getDeclaredField("mObservers")
            mObserversFeild.isAccessible = true
            //获取到这个成员变量的对象
            val mObserversObject: Any = mObserversFeild.get(this)
            //得到map对应的class对象
            val mObserversClass: Class<*> = mObserversObject.javaClass
            //需要执行get方法
            val get: Method = mObserversClass.getDeclaredMethod("get", Any::class.java)
            get.isAccessible = true
            val invokeEntry: Any = get.invoke(mObserversObject, observer)
            var observerWrapper: Any? = null
            if (invokeEntry != null && invokeEntry is Map.Entry<*, *>) {
                observerWrapper = invokeEntry.value
            }
            if (observerWrapper == null) {
                throw NullPointerException("observerWraper is null!")
            }
            //得到ObserveWrapper的类对象 ,编译擦除问题
            val superclass: Class<*> = observerWrapper.javaClass.superclass
            val mLastVersion: Field = superclass.getDeclaredField("mLastVersion")
            mLastVersion.isAccessible = true
            //2.得到mVersion
            val mVersion: Field = liveDataClass.getDeclaredField("mVersion")
            mVersion.isAccessible = true
            //3.mLastVersion填到mVersion中
            val mVersionValue: Any = mVersion.get(this)
            mLastVersion.set(observerWrapper, mVersionValue)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}