package com.lven.baseproject

import android.util.Log
import com.lven.base.AppFragment
import com.lven.base.BindFragment
import com.lven.baseproject.databinding.FragmentFragmentBinding
import kotlinx.android.synthetic.main.fragment_fragment.*

class FragmentTest : BindFragment<TestViewModel, FragmentFragmentBinding>() {

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

    override fun onBind(binding: FragmentFragmentBinding, viewModel: TestViewModel) {
        binding.vm = viewModel
    }

    override fun initViews() {
        viewModel.name.value = "position:$position"
        //tv.text = "position:$position"
    }

    override fun initNet() {
        //tv.text = "initNet:$position"
    }
}