package com.lven.baseproject

import android.util.Log
import androidx.lifecycle.Observer
import com.lven.base.BindFragment
import com.lven.baseproject.databinding.FragmentFragmentBinding

class FragmentTest : BindFragment<FragmentTestViewModel, FragmentFragmentBinding>() {

    var position = 0


    override fun isNeedLoading(): Boolean {
        return false
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_fragment
    }

    override fun initData() {
        arguments?.let {
            position = it.getInt("position", 0)
        }
    }


    override fun onFragmentVisible(isVisible: Boolean) {
        Log.e("TAG", "onFragmentVisible:$isVisible:$position")
    }

    override fun onBind(binding: FragmentFragmentBinding, viewModel: FragmentTestViewModel) {
        binding.vm = viewModel
    }

    override fun initViews() {
        // viewModel.name.value = "position:$position"
        //tv.text = "position:$position"
        viewModel.name.observe(this, Observer {
            Log.e("TAG", "observe:$it:$position")
        })
    }

    override fun initNet() {
        //tv.text = "initNet:$position"
    }
}