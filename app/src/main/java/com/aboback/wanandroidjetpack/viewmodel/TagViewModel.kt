package com.aboback.wanandroidjetpack.viewmodel

import androidx.databinding.ObservableField
import com.aboback.base.*
import com.aboback.wanandroidjetpack.R

/**
 * @author jhb
 * @date 2020/10/23
 */
class TagViewModel {
    var mContent = ObservableField("测试")
    var mTextColor = ObservableField(R.color.colorRed.getResColor())
    var mTextSize = ObservableField(R.dimen.sp_12.getResDimen())
    var mDrawable = ObservableField(R.drawable.rect_red_shape.getResDrawable())
}