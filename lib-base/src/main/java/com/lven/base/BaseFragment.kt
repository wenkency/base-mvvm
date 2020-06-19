package com.lven.base

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.fragment.app.Fragment

/**
 * Fragment公共类
 */
abstract class BaseFragment : Fragment() {
    lateinit var rootView: View
    private var isInit = false
    private var isLoaded = false
    lateinit var activity: Activity

    final override fun onAttach(context: Context) {
        super.onAttach(context)
        activity = context as Activity
    }

    final override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initLifecycle()
        initData()
    }

    final override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return initContentView()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (isInit) {
            return
        }
        isInit = true
        initViews()
        initLoading()
        initNet()
    }

    // 使用ViewPager + Fragment会调用这个方法
    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        // 手动调用一下
        lazyLoad(isVisibleToUser)
    }

    // 普通使用
    override fun onHiddenChanged(hidden: Boolean) {
        // 第一创建的时候不会走
        lazyLoad(!hidden)
    }

    private fun lazyLoad(isVisible: Boolean) {
        if (isInit && isVisible && !isLoaded) {
            isLoaded = true
            onLazyLoad()
        }
    }

    /**
     * 懒加载
     */
    open fun onLazyLoad() {

    }

    /**
     * 1. 初始化生命周期监听
     */
    open fun initLifecycle() {

    }

    /**
     * 2. 初始化从上个页面过来的数据
     */
    open fun initData() {
    }

    /**
     * 3. 初始化ContentView
     */
    private fun initContentView(): View {
        // 包一层，用于加载显示加载布局
        val parent = FrameLayout(getAppActivity())
        rootView = getRootView(parent)
        parent.addView(
            rootView, FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT
            )
        )
        return parent
    }

    open fun getRootView(container: ViewGroup?): View {
        return layoutInflater.inflate(getLayoutId(), container, false)
    }

    /**
     * 3. 设置ContentView
     */
    abstract fun getLayoutId(): Int


    /**
     * 5. 初始化View
     */
    open fun initViews() {
    }

    /**
     * 6. 初始化Loading
     */
    open fun initLoading() {

    }

    /**
     * 7. 请求网络
     */
    open fun initNet() {
    }

    fun getAppActivity(): Activity {
        return activity
    }
}