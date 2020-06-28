package com.lven.base.jetpack

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent

/**
 * LifecycleObserver:生命周期观察者
 */
open class BaseLifecycleObserver : LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    open fun onCreate(owner: LifecycleOwner) {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    open fun onStart(owner: LifecycleOwner) {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    open fun onResume(owner: LifecycleOwner) {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    open fun onPause(owner: LifecycleOwner) {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    open fun onStop(owner: LifecycleOwner) {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    open fun onDestroy(owner: LifecycleOwner) {
        // 这个是移除自己
        owner.lifecycle.removeObserver(this)
    }

    /**
     * 只有ON_ANY状态下，才可以接收两个参数
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
    open fun onAny(owner: LifecycleOwner, event: Lifecycle.Event) {
    }
}