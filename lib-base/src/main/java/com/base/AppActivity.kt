package com.base

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
                .setBackImageRes(titleBackImageRes())
                .setBackImageFilterColor(titleBackImageFilterColor())
                .build()
            // 主题风格
            titleBar?.let {
                it.colorStyle(titleColor(), titleContentColor(), titleDark(), titleTrans())
                // 标题字体颜色
                it.setTitleColor(titleTextColor())
                it.setRightTextColor(titleRightTextColor())
                // 初始化设置Title
                initTitle(it)
            }

        }
    }

    // 标题相关============================================
    // 返回按钮图片
    open fun titleBackImageRes() = BaseConfig.IC_TITLE_BACK

    // 返回按钮图片过滤色，默认为白色
    open fun titleBackImageFilterColor() = BaseConfig.IC_TITLE_BACK_FILTER_COLOR

    // 标题颜色
    open fun titleColor() = BaseConfig.TITLE_CONTENT_COLOR

    // 内容颜色
    open fun titleContentColor() = BaseConfig.CONTENT_COLOR

    // 状态字体是否为黑色，默认白色
    open fun titleDark() = BaseConfig.TITLE_DARK

    // 如果true 就是fitSystem为false 反之就是 true
    open fun titleTrans() = BaseConfig.TITLE_TRANS

    // 标题字体颜色
    open fun titleTextColor() = BaseConfig.TITLE_TEXT_COLOR

    // 标题右边字体颜色
    open fun titleRightTextColor() = BaseConfig.TITLE_RIGHT_TEXT_COLOR
    // 标题相关============================================

    /**
     * 要不要标题，默认是要的
     */
    open fun isNeedTitle(): Boolean {
        return true
    }

    abstract fun initTitle(titleBar: DefTitleBar)

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


    open fun showLoading(isShowContent: Boolean = false) {
        loadingManager?.showLoading(isShowContent)
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