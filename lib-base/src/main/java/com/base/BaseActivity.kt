package com.base

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.base.jetpack.BaseLifecycleObserver
import com.base.jetpack.LiveDataBus
import com.base.utils.FragmentUtils
import com.base.utils.KeyBordUtils
import com.base.utils.ViewModelUtils
import com.boardour.libmvvm.R

/**
 * 通用的Activity基类
 */
abstract class BaseActivity : AppCompatActivity() {


    lateinit var rootView: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 1. 初始化生命周期监听
        registerLifecycle()
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
    private fun registerLifecycle() {
        // 这里是自动关闭软件键盘
        lifecycle.addObserver(object : BaseLifecycleObserver() {
            override fun onDestroy(owner: LifecycleOwner) {
                release()
                super.onDestroy(owner)
            }
        })
        initLifecycle()
    }


    /**
     * Activity销毁调用
     */
    fun release() {
        // 关闭键盘
        KeyBordUtils.closeKeyBord(getAppActivity())
        // 清除Fragment
        FragmentUtils.removeAllFragments(supportFragmentManager)
        // 关闭Dialog
        dismissDialog(true)
        // 移除转输的数据
        if (isClearLiveData()) {
            LiveDataBus.clear()
        }
    }

    /**
     * 清除LiveDataBus发送的数据
     */
    open fun isClearLiveData(): Boolean {
        return true
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


    fun getAppActivity(): Activity {
        return this
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

    override fun startActivityForResult(intent: Intent?, requestCode: Int, options: Bundle?) {
        super.startActivityForResult(intent, requestCode, options)
        // 启动页面的动画
        if (isStartActivityAnim()) {
            startActivityAnim()
        }
    }

    override fun finish() {
        super.finish()
        // 关闭页面的动画
        if (isFinishActivityAnim()) {
            finishActivityAnim()
        }
    }

    // 是否开启启动页面的动画
    open fun isStartActivityAnim(): Boolean {
        return true
    }

    // 可以复写这个页面改变启动动画
    open fun startActivityAnim() {
        overridePendingTransition(R.anim.activity_new, R.anim.activity_out)
    }

    // 是否开启关闭页面的动画
    open fun isFinishActivityAnim(): Boolean {
        return true
    }

    // 可以复写这个页面改变关闭动画
    open fun finishActivityAnim() {
        overridePendingTransition(R.anim.activity_back, R.anim.activity_finish)
    }

    // ===ViewModel 提供一些通用的方法子类用======================================================
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
        return ViewModelUtils.getAndroidViewModel(this, application, clazz)
    }

    /**
     * 只有一份，公共用的，数据共享
     */
    open fun <T : ViewModel> getShareViewModel(clazz: Class<T>): T {
        return ViewModelUtils.getShareViewModel(application, clazz)
    }
}
