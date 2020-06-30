package com.lven.base

import android.view.View
import com.lven.loading.LoadState
import com.lven.loading.LoadingLayout
import com.lven.loading.LoadingManager
import com.lven.loading.OnLoadingListener
import com.lven.loading.listener.AppLoadingListener
import com.lven.loading.listener.AppPagerListener

/**
 * 带标题和加载页面的Fragment
 */
abstract class AppFragment : BaseFragment(), AppPagerListener {


    var loadingManager: LoadingManager? = null


    /**
     * 6. 初始化Loading
     */
    override fun initLoading() {
        if (isNeedLoading) {
            loadingManager = LoadingManager.generate(loadingParent, onLoadingListener)
            afterInitLoading()
        }
    }

    /**
     * 初始化Loading后调用
     */
    open fun afterInitLoading() {
        showLoading()
    }

    /**
     * 定制空页面
     */
    override fun getEmptyLayoutId(): Int {
        return -1
    }

    /**
     * 加载布局完成时的回调
     */
    override fun onViewLoaded(state: LoadState, view: View) {
        when (state) {
            // 重试的数据加载失败点击默认事件
            LoadState.DATA_ERROR, LoadState.RETRY -> {
                view.setOnClickListener {
                    showLoading()
                    initNet()
                }
            }
        }
    }

    /**
     * 加载页面状态改变时的回调
     */
    override fun onLoadingChanged(state: LoadState, loadingLayout: LoadingLayout) {

    }

    override fun getOnLoadingListener(): OnLoadingListener {
        return AppLoadingListener(this)
    }

    override fun getLoadingParent(): Any {
        return rootView
    }

    override fun isNeedLoading(): Boolean {
        return true
    }


    open fun showLoading() {
        loadingManager?.showLoading()
    }

    open fun showRetry() {
        loadingManager?.showRetry()
    }

    open fun showNetOrDataError() {
        loadingManager?.showNetOrDataError(activity.applicationContext)
    }

    open fun showContent() {
        loadingManager?.showContent()
    }

    open fun showEmpty() {
        loadingManager?.showEmpty()
    }
}