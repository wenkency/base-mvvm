package com.boardour.basemvvm

import android.content.Intent
import android.util.Log
import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import cn.carhouse.titlebar.DefTitleBar
import com.base.jetpack.BaseLifecycleObserver
import com.boardour.basemvvm.databinding.ActivityMainBinding
import com.boardour.basemvvm.comm.ShareActivity
import com.boardour.basemvvm.viewmodel.MainViewModel

class MainActivity : ShareActivity<MainViewModel, ActivityMainBinding>() {

    override fun initTitle(titleBar: DefTitleBar) {
        titleBar.setTitle("主页面")
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun onBind(binding: ActivityMainBinding, viewModel: MainViewModel) {
        binding.viewModel = viewModel
        binding.click = MainClick()
    }

    override fun initLifecycle() {
        // 在这里对
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