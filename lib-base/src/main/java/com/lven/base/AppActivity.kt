package com.lven.base

import android.view.View
import cn.carhouse.titlebar.DefTitleBar
import cn.carhouse.titlebar.DefTitleBuilder
import com.lven.loading.LoadState
import com.lven.loading.LoadingManager
import com.lven.loading.OnLoadingListener
import com.lven.loading.listener.AppLoadingListener
import com.lven.loading.listener.AppPagerListener

/**
 * 带标题和加载页面的Activity
 */
abstract class AppActivity : BaseActivity(), AppPagerListener {

    var titleBar: DefTitleBar? = null

    var loadingManager: LoadingManager? = null


    /**
     * 4. 初始化标题
     */
    override fun initTitle() {
        // 初始化标题
        if (isNeedTitle()) {
            titleBar = DefTitleBuilder(this) // 返回按钮
                .setBackImageRes(TitleBarConfig.IC_TITLE_BACK)
                .setBackImageFilterColor(TitleBarConfig.IC_TITLE_BACK_FILTER_COLOR)
                .build()
            // 主题风格
            titleBar!!.colorStyle(TitleBarConfig.TITLE_CONTENT_COLOR, TitleBarConfig.CONTENT_COLOR)
            // 标题字体颜色
            titleBar!!.setTitleColor(TitleBarConfig.TITLE_TEXT_COLOR)
            titleBar!!.setRightTextColor(TitleBarConfig.TITLE_RIGHT_TEXT_COLOR)
            // 初始化设置Title
            initTitle(titleBar!!)
        }
    }

    /**
     * 要不要标题，默认是要的
     */
    open fun isNeedTitle(): Boolean {
        return true
    }

    abstract fun initTitle(titleBar: DefTitleBar);

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


    open fun showLoading() {
        loadingManager?.showLoading()
    }

    open fun showRetry() {
        loadingManager?.showRetry()
    }

    open fun showNetOrDataError() {
        loadingManager?.showNetOrDataError(applicationContext)
    }

    open fun showContent() {
        loadingManager?.showContent()
    }

    open fun showEmpty() {
        loadingManager?.showEmpty()
    }
}