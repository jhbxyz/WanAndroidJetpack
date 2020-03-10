package com.jhb.wanandroidjetpack.bridge

import androidx.lifecycle.ViewModel

/**
 * Created by jhb on 2020-02-11.
 */
class ShareViewModel : ViewModel() {

    val homeNavClick = UnPeekLiveData<Int>()

}