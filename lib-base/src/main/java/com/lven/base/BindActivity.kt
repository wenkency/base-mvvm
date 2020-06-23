package com.lven.base

import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.lven.base.jetpack.BaseViewModel
import java.lang.reflect.ParameterizedType

/**
 * 使用DataBinding开发的Activity
 */
abstract class BindActivity<M : ViewModel, T : ViewDataBinding> : AppActivity() {


    lateinit var binding: T
    lateinit var viewModel: M


    override fun initContentView() {
        // 这里是设置布局数据
        binding = DataBindingUtil.setContentView(this, getLayoutId())
        rootView = binding.root
        binding.lifecycleOwner = this

        // 这里是创建ViewModel
        val type = javaClass.genericSuperclass as ParameterizedType
        val viewModelClazz = type.actualTypeArguments[0] as Class<M>
        viewModel = getViewModel(viewModelClazz)

        // 绑定操作，给子类去实现
        onBind(binding, viewModel)
    }

    /**
     * 绑定操作，给子类去实现
     */
    abstract fun onBind(binding: T, viewModel: M)

    // ===提供一些通用的方法子类用======================================================
    /**
     * 子类用
     */
    fun <T : ViewModel> getViewModel(clazz: Class<T>): T {
        return ViewModelProvider(this).get(clazz)
    }

    /**
     * 只有一份，公共用的，数据共享
     */
    fun <T : ViewModel> getAppViewModel(clazz: Class<T>): T {
        return BaseViewModel.instance.getViewModel(getAppActivity(), clazz)
    }

}