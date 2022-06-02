package com.lven.baseproject.repository

import androidx.lifecycle.MutableLiveData
import com.base.viewmodel.DialogStateViewModel
import com.base.viewmodel.PageStateViewModel
import com.lven.baseproject.http.DialogCallback
import com.lven.baseproject.http.PageStateCallback
import com.retrofit.RetrofitPresenter


/**
 * 网络请求测试
 */
class NetPresenterViewModel : AutoCancelViewModel() {
    var result = MutableLiveData("")

    /**
     * 网络请求，控制加载页面的
     */
    fun requestNet(pageState:PageStateViewModel) {
        // 网络请求
        RetrofitPresenter.postForm(this, "post", object : PageStateCallback<String>(pageState) {
            override fun onLoadSucceed(data: String) {
                // 更新UI
                result.value = data
            }
        },"formKey","formValue")
    }

    fun requestNetShowDialog(dialogState:DialogStateViewModel) {

        // 网络请求
        RetrofitPresenter.post(
            this, "post",
            object : DialogCallback<String>(dialogState) {
                override fun onLoadSucceed(data: String) {
                    // 更新UI
                    result.value = data
                }
            }, "key", "value"
        )
    }
}