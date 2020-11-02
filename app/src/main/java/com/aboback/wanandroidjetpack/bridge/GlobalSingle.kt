package com.aboback.wanandroidjetpack.bridge

import androidx.lifecycle.MutableLiveData

/**
 * @author jhb
 * @date 2020/11/2
 */
object GlobalSingle {

    val isLoginSuccess = MutableLiveData<Boolean>()
}