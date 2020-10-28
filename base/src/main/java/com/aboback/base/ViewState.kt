package com.aboback.base

import androidx.lifecycle.Observer
import com.aboback.base.ui.BaseActivity
import com.aboback.base.viewmodel.BaseViewModel

/**
 * @author jhb
 * @date 2020/10/22
 */
interface ViewState {

    fun beforeSetView()

    fun onViewInit()

    fun onEvent()

    fun BaseViewModel.dialogState(baseActivity: BaseActivity) {
        isDialogShow.observe(baseActivity, Observer {
            if (it) baseActivity.showDialog() else baseActivity.hideDialog()
        })
    }

}