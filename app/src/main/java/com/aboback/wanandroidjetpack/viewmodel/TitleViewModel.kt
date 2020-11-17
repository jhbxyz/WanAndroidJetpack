package com.aboback.wanandroidjetpack.viewmodel

import android.graphics.drawable.Drawable
import androidx.databinding.ObservableField
import com.aboback.base.util.getDrawable
import com.aboback.base.util.getResColor
import com.aboback.wanandroidjetpack.R

/**
 * Created by jhb on 2020-01-15.
 */
class TitleViewModel(var leftText: String? = "",
                     var leftDrawable: Drawable? = R.drawable.abc_vector_test.getDrawable(),
                     var leftAction: (() -> Unit)? = null,
                     var title: String = "",
                     var rightText: String = "",
                     var rightDrawable: Drawable? = null,
                     var rightAction: (() -> Unit)? = null,
                     var background: Int = R.color.colorAccent.getResColor()

) {
    val mTitle = ObservableField(title)
    val mRightDrawable = ObservableField(rightDrawable)
}