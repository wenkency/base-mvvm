package com.base

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.base.jetpack.BaseLifecycleObserver
import com.base.utils.ViewModelUtils

/**
 * Fragment公共类
 */
abstract class BaseFragment : Fragment() {
    lateinit var rootView: View

    // 是不是第一次加载
    private var isFirstLoaded = true
    private var isInit = false

    lateinit var mActivity: Activity

    private var parent: FrameLayout? = null

    final override fun onAttach(context: Context) {
        super.onAttach(context)
        mActivity = context as Activity
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        registerLifecycle()
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
        initViews()
        initLoading()
        isInit = true
    }

    /**
     * 1. 初始化生命周期监听
     */
    private fun registerLifecycle() {
        // 这里来实现懒加载
        lifecycle.addObserver(object : BaseLifecycleObserver() {
            override fun onResume(owner: LifecycleOwner) {
                dispatchVisible(true)
            }

            override fun onPause(owner: LifecycleOwner) {
                dispatchVisible(false)
                dismissDialog(true)
            }
        })
        initLifecycle()
    }

    open fun initLifecycle() {

    }

    override fun onHiddenChanged(hidden: Boolean) {
        dispatchVisible(!hidden)
    }

    private fun dispatchVisible(isVisible: Boolean) {
        if (isVisible && isFirstLoaded) {
            isFirstLoaded = false
            initNet()
        }
        onFragmentVisible(isVisible)
    }

    /**
     * 提供给子类用
     */
    open fun onFragmentVisible(isVisible: Boolean) {

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
        if (parent != null) {
            parent?.let {
                // 这里没走，防止空，还是留着
                if (it.parent != null) {
                    val viewGroup = it.parent as ViewGroup
                    viewGroup.removeView(parent)
                }
            }
            return parent!!
        }
        parent = FrameLayout(getAppActivity())
        // 包一层，用于加载显示加载布局
        rootView = getRootView(parent)
        parent?.addView(
            rootView, FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT
            )
        )
        return parent!!
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
        return mActivity
    }

    // ===提供一些通用的方法子类用======================================================

    // 每个项目显示的Dialog不一样，交给子类实现
    open fun showDialog() {
        BaseConfig.showDialog(getAppActivity())
    }

    // 每个项目显示的Dialog不一样，交给子类实现
    open fun dismissDialog(isDestroy: Boolean = false) {
        BaseConfig.dismissDialog(isDestroy)
    }


    open fun <V : View?> findViewById(id: Int): V {
        return rootView.findViewById<V>(id)
    }

    /**
     * 打开Activity
     */
    fun startActivity(clazz: Class<*>?, options: Bundle? = null, requestCode: Int = 0) {
        val intent = Intent(getAppActivity(), clazz)
        if (options != null) {
            intent.putExtras(options)
        }
        startActivityForResult(intent, requestCode)
    }

    // ===提供一些通用的方法子类用======================================================
    /**
     * 子类用
     */
    open fun <T : ViewModel> getViewModel(clazz: Class<T>): T {
        return ViewModelUtils.getViewModel(this, clazz)
    }

    /**
     * 子类用
     */
    open fun <T : ViewModel> getAndroidViewModel(clazz: Class<T>): T {
        return ViewModelUtils.getAndroidViewModel(this, getAppActivity().application, clazz)
    }

    /**
     * 只有一份，公共用的，数据共享
     */
    open fun <T : ViewModel> getShareViewModel(clazz: Class<T>): T {
        return ViewModelUtils.getShareViewModel(getAppActivity().application, clazz)
    }
}