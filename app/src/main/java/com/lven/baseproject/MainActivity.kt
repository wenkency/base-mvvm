package com.lven.baseproject

import android.content.Intent
import android.util.Log
import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import cn.carhouse.titlebar.DefTitleBar
import com.lven.base.jetpack.BaseLifecycleObserver
import com.lven.baseproject.comm.ShareActivity
import com.lven.baseproject.databinding.ActivityMainBinding

class MainActivity : ShareActivity<MainViewModel, ActivityMainBinding>() {

    override fun initTitle(titleBar: DefTitleBar) {
        titleBar.setTitle("主页面")
        // 不要返回按钮
        titleBar.clearBackImage()
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun onBind(binding: ActivityMainBinding, viewModel: MainViewModel) {
        binding.viewModel = viewModel
        binding.click = MainClick()
    }

    override fun initLifecycle() {
        lifecycle.addObserver(object : BaseLifecycleObserver() {
            override fun onAny(owner: LifecycleOwner, event: Lifecycle.Event) {
                Log.e("TAG", event.name)
            }
        })
    }

    override fun initViews() {
        // 这里是监听通知，在OtherActivity点击的时候发生改变
        shareViewModel.shareName.observe(this, Observer {
            // 在START或者RESUME状态的时候，才会刷新
            Log.e("TAG", "change:$it")
            viewModel.name.value = it
        })

    }

    override fun initNet() {
        // 模拟网络请求
        viewModel.request()
    }

    inner class MainClick {
        fun click(view: View) {
            startActivity(Intent(getAppActivity(), OtherActivity::class.java))
        }
    }

    /**
     * 不需要加载页面
     */
    override fun isNeedLoading(): Boolean {
        return false
    }
}