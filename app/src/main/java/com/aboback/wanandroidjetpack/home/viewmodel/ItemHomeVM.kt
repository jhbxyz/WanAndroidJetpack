package com.aboback.wanandroidjetpack.home.viewmodel

import android.app.Application
import androidx.databinding.ObservableField
import com.aboback.wanandroidjetpack.base.BaseItemViewModel

/**
 * @author jhb
 * @date 2020/10/23
 */
class ItemHomeVM(app: Application) : BaseItemViewModel(app) {
    var mContent = ObservableField("")
}