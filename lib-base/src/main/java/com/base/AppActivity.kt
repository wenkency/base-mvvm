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
    // 标题
    var titleBar: DefTitleBar? = null

    // 加载页面:内容页面、空页面、重试页面、加载页面、数据错误页面
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
                it.setRightTextColor(titleRightTextColor())
                // 标题字体颜色
                it.setTitleColor(titleTextColor())
                if (navigationBarTrans()) {
                    it.navigationBarTrans()
                }
                if (hideNavigationBar()) {
                    it.hideNav(titleDark())
                }

                // 初始化设置Title
                initTitle(it)
            }

        }
    }

    // 标题相关============================================
    open fun hideNavigationBar() = BaseConfig.hideNavigationBar

    // 虚拟键盘透明
    open fun navigationBarTrans() = BaseConfig.navigationBarTrans

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

    // 显示加载中页面时，是否显示内容页面
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

    // 加载页面的父类
    override fun getLoadingParent(): Any {
        return rootView
    }

    // 是否需要加载页面
    override fun isNeedLoading(): Boolean {
        return true
    }

    // 显示加载页面，默认不显示内容页面
    open fun showLoading(isShowContent: Boolean = false) {
        loadingManager?.showLoading(isShowContent)
    }

    // 显示重试页面
    open fun showRetry() {
        loadingManager?.showRetry()
    }

    // 显示数据出错页面
    open fun showNetOrDataError() {
        loadingManager?.showNetOrDataError(applicationContext)
    }

    // 显示内容页面
    open fun showContent() {
        loadingManager?.showContent()
    }

    // 显示空页面
    open fun showEmpty() {
        loadingManager?.showEmpty()
    }
}