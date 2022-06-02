package com.lven.baseproject.viewmodel.page

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * 后面有空再写，可以先不写，没必要
 */
class PageStateViewModel : ViewModel() {
    // loading empty content error data_error
    var pageState: MutableLiveData<PageState> = MutableLiveData()

    // show dismiss
    var dialogState: MutableLiveData<DialogState> = MutableLiveData()
}