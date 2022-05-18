package com.lven.baseproject

import cn.carhouse.titlebar.DefTitleBar
import com.base.BindActivity
import com.lven.baseproject.databinding.ActivityTestBinding

import com.lven.baseproject.viewmodel.EmptyViewModel

/**
 * 测试入口
 */
class TestActivity : BindActivity<EmptyViewModel, ActivityTestBinding>() {
    override fun initTitle(titleBar: DefTitleBar) {
        titleBar.setTitle("测试")
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_test
    }

    override fun onBind(binding: ActivityTestBinding, viewModel: EmptyViewModel) {
        binding.click = TestClick()
    }

    inner class TestClick {
        fun normal() {
            startActivity(NormalActivity::class.java)
        }

        fun mvvm() {
            startActivity(MVVMActivity::class.java)
        }

        fun other() {
            startActivity(OtherActivity::class.java)
        }

        fun fmt() {
            startActivity(FragmentActivity::class.java)
        }

        fun fmtViewPager() {
            startActivity(FragmentViewPagerActivity::class.java)
        }

        fun main() {
            startActivity(MainActivity::class.java)
        }

        fun net() {
            startActivity(NetMvvmActivity::class.java)
        }
    }

}