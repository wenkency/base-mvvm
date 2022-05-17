package com.lven.base

import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.lven.base.jetpack.BaseViewModel
import com.lven.base.utils.ViewModelUtils
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
        // 主要是双向绑定，内容变了，XML也跟着变
        binding.lifecycleOwner = this

        // 这里是创建ViewModel
        val type = javaClass.genericSuperclass as ParameterizedType
        val viewModelClazz = type.actualTypeArguments[0] as Class<M>

        viewModel = if (isAndroidViewModel()) {
            getAndroidViewModel(viewModelClazz)
        } else {
            getViewModel(viewModelClazz)
        }
        // 绑定操作，给子类去实现
        onBind(binding, viewModel)
    }

    /**
     * 如果是AndroidViewModel的子类，就复写这个方法返回true
     */
    open fun isAndroidViewModel(): Boolean {
        return false
    }

    /**
     * 绑定操作，给子类去实现
     */
    abstract fun onBind(binding: T, viewModel: M)

    // ===提供一些通用的方法子类用======================================================
    /**
     * 子类用
     */
    open fun <T : ViewModel> getViewModel(clazz: Class<T>): T {
        return ViewModelUtils.getViewModel(this, clazz)
    }

    /**
     * 子类用
     */
    open fun <T : ViewModel> getAndroidViewModel(clazz: Class<T>): T {
        return ViewModelUtils.getAndroidViewModel(this, application, clazz)
    }

    /**
     * 只有一份，公共用的，数据共享
     */
    open fun <T : ViewModel> getAppViewModel(clazz: Class<T>): T {
        return ViewModelUtils.getAppViewModel(getAppActivity(), clazz)
    }

}