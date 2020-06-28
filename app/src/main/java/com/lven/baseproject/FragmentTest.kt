package com.lven.baseproject

import android.util.Log
import com.lven.base.AppFragment
import kotlinx.android.synthetic.main.fragment_fragment.*

class FragmentTest : AppFragment() {

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

    override fun initViews() {
        tv.text = "position:$position"
    }

    override fun initNet() {
        tv.text = "initNet:$position"
    }

    override fun onFragmentVisible(isVisible: Boolean) {
        Log.e("TAG", "onFragmentVisible:$isVisible:$position")
    }


}