package com.boardour.basemvvm

import android.util.Log
import com.base.BindFragment
import com.boardour.basemvvm.databinding.FragmentFragmentBinding
import com.boardour.basemvvm.viewmodel.FragmentTestViewModel

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
        Log.e("TAG", "onFragmentVisible:$position:$isVisible")
        // viewModel.name.postValue("onFragmentVisible:$isVisible:$position")
    }

    override fun onBind(binding: FragmentFragmentBinding, viewModel: FragmentTestViewModel) {
        binding.vm = viewModel
    }

    override fun initViews() {
        viewModel.name.value = "position:$position"
        Log.e("TAG", "create:$position")
        // tv.text = "position:$position"
        /* viewModel.name.observe(this, Observer {
             Log.e("TAG", "observe:$it:$position")
         })*/
    }

    override fun initNet() {
        //tv.text = "initNet:$position"
    }
}