package com.base

import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import com.base.utils.ViewModelUtils
import com.base.viewmodel.DialogStateViewModel
import com.base.viewmodel.PageStateViewModel
import java.lang.reflect.ParameterizedType

/**
 * 使用DataBinding开发的Fragment
 */
abstract class BindFragment<M : ViewModel, T : ViewDataBinding> : AppFragment() {


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

    /**
     * 3. 初始化ContentView
     */
    override fun getRootView(container: ViewGroup?): View {
        binding = DataBindingUtil.inflate(layoutInflater, getLayoutId(), container, false)
        binding.lifecycleOwner = this

        // 这里是创建ViewModel
        val type = javaClass.genericSuperclass as ParameterizedType
        val viewModelClazz = type.actualTypeArguments[0] as Class<M>

        viewModel = if (isAndroidViewModel()) {
            getAndroidViewModel(viewModelClazz)
        } else {
            getViewModel(viewModelClazz)
        }

        // 提供子类用
        onBind(binding, viewModel)

        return binding.root
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
     * 提供子类绑定用
     */
    abstract fun onBind(binding: T, viewModel: M)


}