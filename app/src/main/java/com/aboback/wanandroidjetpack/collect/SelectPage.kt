package com.aboback.wanandroidjetpack.collect

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.aboback.wanandroidjetpack.bridge.GlobalSingle

/**
 * @author jhb
 * @date 2020/11/5
 */
interface SelectPage {

    fun pageIndex(): Int = -1

    fun onSelectPage()

    fun LifecycleOwner.register() {
        GlobalSingle.onFindPageSelect.observe(this, Observer {
            if (it == pageIndex()) {
                onSelectPage()
                GlobalSingle.onFindPageSelect.value = -1
            }
        })
    }
}