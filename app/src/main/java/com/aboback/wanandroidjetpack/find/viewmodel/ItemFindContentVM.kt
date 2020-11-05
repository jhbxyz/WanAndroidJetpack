package com.aboback.wanandroidjetpack.find.viewmodel

import android.app.Application
import androidx.databinding.ObservableField
import com.aboback.base.rv.BaseItemViewModel

/**
 * @author jhb
 * @date 2020/11/5
 */
class ItemFindContentVM(app: Application) : BaseItemViewModel(app) {
    var mContent = ObservableField<String>()
}