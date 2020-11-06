package com.aboback.wanandroidjetpack.find.viewmodel

import android.app.Application
import androidx.databinding.ObservableField
import com.aboback.base.rv.BaseItemViewModel
import com.aboback.base.util.getRandomColor

/**
 * @author jhb
 * @date 2020/11/5
 */
class ItemFindContentTreeAndNaviRightVM(app: Application) : BaseItemViewModel(app) {
    var mBgColor = ObservableField(getRandomColor())
    var mContent = ObservableField<String>()
    var onClickItem = {}
}