package com.lven.base

import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.lven.base.jetpack.BaseViewModel
import java.lang.reflect.ParameterizedType

/**
 * 使用DataBinding开发的Fragment
 */
abstract class BindFragment<M : ViewModel, T : ViewDataBinding> : AppFragment() {


    lateinit var binding: T
    lateinit var viewModel: M

    /**
     * 3. 初始化ContentView
     */
    override fun getRootView(container: ViewGroup?): View {
        binding = DataBindingUtil.inflate(layoutInflater, getLayoutId(), container, false)
        binding.lifecycleOwner = this

        // 这里是创建ViewModel
        val type = javaClass.genericSuperclass as ParameterizedType
        val viewModelClazz = type.actualTypeArguments[0] as Class<M>
        viewModel = getViewModel(viewModelClazz)

        // 提供子类用
        bind(binding, viewModel)

        return binding.root
    }

    /**
     * 提供子类绑定用
     */
    abstract fun bind(binding: T, viewModel: M)
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