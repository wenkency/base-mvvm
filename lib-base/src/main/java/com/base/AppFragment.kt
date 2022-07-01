package com.base

import android.view.View
import com.lven.loading.LoadState
import com.lven.loading.LoadingManager
import com.lven.loading.OnLoadingListener
import com.lven.loading.listener.AppLoadingListener
import com.lven.loading.listener.AppPagerListener

/**
 * 加载页面的Fragment
 */
abstract class AppFragment : BaseFragment(), AppPagerListener {


    var loadingManager: LoadingManager? = null


    /**
     * 6. 初始化Loading
     */
    override fun initLoading() {
        if (isNeedLoading) {
            loadingManager = LoadingManager.generate(loadingParent, onLoadingListener)
            onLoadingInit()
            afterInitLoading()
        }
    }

    open fun onLoadingInit() {

    }

    /**
     * 初始化Loading后调用，如有需要可以重写这个方法去调用showLoading()
     */
    open fun afterInitLoading() {
        showContent()
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
                    showLoading(isShowContent())
                    initNet()
                }
            }
        }
    }

    open fun isShowContent(): Boolean {
        return false
    }

    /**
     * 加载页面状态改变时的回调
     */
    override fun onLoadingChanged(state: LoadState, view: View) {

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


    open fun showLoading(isShowContent: Boolean) {
        loadingManager?.showLoading(isShowContent)
    }

    open fun showRetry() {
        loadingManager?.showRetry()
    }

    open fun showNetOrDataError() {
        loadingManager?.showNetOrDataError(mActivity.applicationContext)
    }

    open fun showContent() {
        loadingManager?.showContent()
    }

    open fun showEmpty() {
        loadingManager?.showEmpty()
    }
}