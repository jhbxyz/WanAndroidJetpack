package com.aboback.wanandroidjetpack.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aboback.wanandroidjetpack.bean.BaseBean
import com.aboback.wanandroidjetpack.bean.WendaListBean
import com.aboback.wanandroidjetpack.network.NetConstant
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/**
 * @author jhb
 * @date 2020/10/27
 */
fun ViewModel.launch(result: suspend () -> Unit) {
    viewModelScope.launch {
        try {

        } catch (e: Throwable) {
            e.printStackTrace()
        }
    }
}

fun response(bean: BaseBean, result: suspend () -> Unit) {
    when (bean.errorCode) {
        NetConstant.SUCCESS -> {

        }
        else -> {
        }
    }
}
