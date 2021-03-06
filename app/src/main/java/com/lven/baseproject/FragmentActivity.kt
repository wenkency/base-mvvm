package com.lven.baseproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import cn.carhouse.titlebar.DefTitleBar
import com.lven.base.BindActivity
import com.lven.base.utils.FragmentUtils
import com.lven.baseproject.databinding.ActivityFragmentBinding

class FragmentActivity : BindActivity<FragmentViewModel, ActivityFragmentBinding>() {
    var map = mutableMapOf<Int, Fragment>()
    override fun isNeedLoading(): Boolean {
        return false
    }

    override fun initTitle(titleBar: DefTitleBar) {
        titleBar.setTitle("FragmentActivity")
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_fragment
    }

    override fun onBind(binding: ActivityFragmentBinding, viewModel: FragmentViewModel) {
        binding.viewModel = viewModel
        binding.click = OnClick()
    }

    inner class OnClick {
        fun onClick(position: Int) {
            selectFragment(position)
        }
    }

    override fun initViews() {
        selectFragment(0)
    }

    private fun selectFragment(position: Int) {
        var findFragment = map[position]
        if (findFragment == null) {
            findFragment = FragmentTest().apply {
                arguments = Bundle()
                arguments!!.putInt("position", position)
            }
            map[position] = findFragment
        }
        FragmentUtils.changeFragment(supportFragmentManager, R.id.fl_container, findFragment)

    }


}