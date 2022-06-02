package com.base

import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import com.base.utils.ViewModelUtils
import com.base.viewmodel.DialogStateViewModel
import com.base.viewmodel.PageStateViewModel
import java.lang.reflect.ParameterizedType

/**
 * 使用DataBinding开发的Activity
 */
abstract class BindActivity<M : ViewModel, T : ViewDataBinding> : AppActivity() {


    lateinit var binding: T
    lateinit var viewModel: M

    // 控制页面状态的ViewModel
    val pageState: PageStateViewModel by lazy {
        getViewModel(PageStateViewModel::class.java)
    }
    val dialogState: DialogStateViewModel by lazy {
        getViewModel(DialogStateViewModel::class.java)
    }

    // 控制Dialog
    override fun initLifecycle() {
        dialogState.showDialog.observe(this) {
            if (it) {
                showDialog()
            }
        }
        dialogState.dismissDialog.observe(this) {
            if (it) {
                dismissDialog(dialogState.isDestroy.value!!)
            }
        }
    }

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

    // 这里监听页面显示状态
    final override fun onLoadingInit() {
        pageState.showContent.observe(this) {
            if (it) {
                showContent()
            }
        }
        pageState.showEmpty.observe(this) {
            if (it) {
                showEmpty()
            }
        }
        pageState.showNetOrDataError.observe(this) {
            if (it) {
                showNetOrDataError()
            }
        }
        pageState.showLoading.observe(this) {
            if (it) {
                showLoading(pageState.showLoadingContent.value!!)
            }
        }
        pageState.showRetry.observe(this) {
            if (it) {
                showRetry()
            }
        }
    }

    override fun afterInitLoading() {

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
    open fun <T : ViewModel> getShareViewModel(clazz: Class<T>): T {
        return ViewModelUtils.getShareViewModel(application, clazz)
    }

}