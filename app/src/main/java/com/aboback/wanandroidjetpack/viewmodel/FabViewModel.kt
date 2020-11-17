package com.aboback.wanandroidjetpack.viewmodel

import android.graphics.drawable.Drawable
import androidx.databinding.ObservableField
import com.aboback.base.util.getDrawable
import com.aboback.base.util.getResColor
import com.aboback.base.util.getResDimen
import com.aboback.wanandroidjetpack.R

/**
 * @author jhb
 * @date 2020/10/29
 */
class FabViewModel(var size: Int = R.dimen.dp_50.getResDimen().toInt(),
                   var drawable: Drawable? = R.drawable.up_arrow_white.getDrawable(),
                   var onClick: (() -> Unit)? = null,
                   var background: Int = R.color.colorAccent.getResColor()) {
    val mDrawable = ObservableField(drawable)
}