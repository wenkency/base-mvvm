package com.lven.base

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import com.lven.base.jetpack.BaseLifecycleObserver
import com.lven.base.utils.FragmentUtils
import com.lven.base.utils.KeyBordUtils

/**
 * 通用的Activity基类
 */
abstract class BaseActivity : AppCompatActivity() {


    lateinit var rootView: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 1. 初始化生命周期监听
        initLifecycle()
        // 2. 初始化从上个页面过来的数据
        initData()
        // 3. 设置ContentView
        initContentView()
        // 4. 初始化标题
        initTitle()
        // 5. 初始化View
        initViews()
        // 6. 初始化Loading
        initLoading()
        // 7. 请求网络
        initNet()
    }

    /**
     * 1. 初始化生命周期监听
     */
    open fun initLifecycle() {
        // 这里是自动关闭软件键盘
        lifecycle.addObserver(object : BaseLifecycleObserver() {
            override fun onDestroy(owner: LifecycleOwner) {
                super.onDestroy(owner)
                KeyBordUtils.closeKeyBord(getAppActivity())
            }
        })
    }

    /**
     * 2. 初始化从上个页面过来的数据
     */
    open fun initData() {
    }

    /**
     * 3. 初始化ContentView
     */
    open fun initContentView() {
        rootView = layoutInflater.inflate(getLayoutId(), null)
        setContentView(rootView)
    }

    /**
     * 3. 设置ContentView
     */
    abstract fun getLayoutId(): Int

    /**
     * 4. 初始化标题
     */
    open fun initTitle() {

    }


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

    override fun onDestroy() {
        super.onDestroy()
        // 关闭软件盘
        KeyBordUtils.closeKeyBord(this)
        // 处理内存泄漏 AndroidX 里面自动处理了
        // KeyBordUtils.fixInputMethodManagerLeak(this)
        // 清除Fragment
        FragmentUtils.removeAllFragments(supportFragmentManager)
    }

    fun getAppActivity(): Activity {
        return this
    }
    // ===提供一些通用的方法子类用======================================================

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
}
