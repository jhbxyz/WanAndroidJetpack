package com.aboback.wanandroidjetpack.viewmodel

import androidx.databinding.ObservableField
import com.aboback.base.util.getRandomColor
import com.aboback.base.util.getResColor
import com.aboback.base.util.getResDimen
import com.aboback.base.util.getResDrawable
import com.aboback.wanandroidjetpack.R

/**
 * @author jhb
 * @date 2020/10/23
 */
class TagViewModel {
    var mBgColor = ObservableField(getRandomColor())
    var mContent = ObservableField("")
    var mTextColor = ObservableField(R.color.colorRed.getResColor())
    var mTextSize = ObservableField(R.dimen.sp_12.getResDimen())
    var mDrawable = ObservableField(R.drawable.rect_red_shape.getResDrawable())
}