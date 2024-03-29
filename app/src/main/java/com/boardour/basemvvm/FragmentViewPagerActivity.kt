package com.boardour.basemvvm

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import cn.carhouse.titlebar.DefTitleBar
import com.base.BindActivity
import com.boardour.basemvvm.databinding.ActivityFragmentViewPagerBinding
import com.boardour.basemvvm.viewmodel.FragmentTestViewModel
import com.boardour.basemvvm.viewmodel.FragmentViewModel

class FragmentViewPagerActivity :
    BindActivity<FragmentViewModel, ActivityFragmentViewPagerBinding>() {
    val map = mutableMapOf<Int, Fragment>()
    override fun isNeedLoading(): Boolean {
        return false
    }

    override fun initTitle(titleBar: DefTitleBar) {
        titleBar.setTitle("FragmentActivity")
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_fragment_view_pager
    }

    override fun onBind(binding: ActivityFragmentViewPagerBinding, viewModel: FragmentViewModel) {

    }


    override fun initViews() {
        // viewPager.offscreenPageLimit = 3
        val viewPager: ViewPager = findViewById(R.id.viewPager)
        viewPager.adapter = object : FragmentPagerAdapter(
            supportFragmentManager,
            BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
        ) {
            override fun getItem(position: Int): Fragment {
                return getFragment(position)
            }

            override fun getCount(): Int {
                return 4
            }
        }
        val vm = getViewModel(FragmentTestViewModel::class.java)
        vm.name.value = "I am a name"
    }

    private fun getFragment(position: Int): Fragment {
        var findFragment = map[position]
        if (findFragment == null) {
            findFragment = FragmentTest().apply {
                arguments = Bundle()
                arguments?.putInt("position", position)
            }
            map[position] = findFragment
        }
        return findFragment
    }


}