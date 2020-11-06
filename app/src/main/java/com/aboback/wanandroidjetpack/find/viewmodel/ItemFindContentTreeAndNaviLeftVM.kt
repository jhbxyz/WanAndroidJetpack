package com.aboback.wanandroidjetpack.find.viewmodel

import android.app.Application
import androidx.databinding.ObservableField
import com.aboback.base.rv.BaseItemViewModel
import com.aboback.base.util.getResColor
import com.aboback.wanandroidjetpack.R

/**
 * @author jhb
 * @date 2020/11/5
 */
class ItemFindContentTreeAndNaviLeftVM(app: Application) : BaseItemViewModel(app) {
    var mBgColor = ObservableField(R.color.colorWhiteDark.getResColor())
    var mContent = ObservableField<String>()
    var mCid: Int? = 0
    var mChecked = ObservableField<Boolean>(false)
    var onClickItem = {}
}