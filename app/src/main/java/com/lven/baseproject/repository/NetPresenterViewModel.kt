package com.lven.baseproject.repository

import androidx.lifecycle.MutableLiveData
import com.base.AppActivity
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
    fun requestNet(activity: AppActivity? = null) {
        // 网络请求
        RetrofitPresenter.postForm(this, "post", object : PageStateCallback<String>(activity) {
            override fun onLoadSucceed(data: String) {
                // 更新UI
                result.value = data
            }
        },"formKey","formValue")
    }

    fun requestNetShowDialog(activity: AppActivity? = null) {

        // 网络请求
        RetrofitPresenter.post(
            this, "post",
            object : DialogCallback<String>(activity) {
                override fun onLoadSucceed(data: String) {
                    // 更新UI
                    result.value = data
                }
            }, "key", "value"
        )
    }
}