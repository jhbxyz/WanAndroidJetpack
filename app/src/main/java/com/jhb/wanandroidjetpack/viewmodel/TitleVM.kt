package com.jhb.wanandroidjetpack.viewmodel

import android.graphics.drawable.Drawable
import com.jhb.wanandroidjetpack.R
import com.jhb.wanandroidjetpack.base.BaseViewModel
import com.jhb.wanandroidjetpack.util.getDrawable

/**
 * Created by jhb on 2020-01-15.
 */
class TitleVM(
        var leftDrawable: Drawable? = R.drawable.abc_vector_test.getDrawable(),
        var leftAction: (() -> Unit)? = null,
        var title: String,
        var rightText: String = "",
        var rightAction: (() -> Unit)? = null

) : BaseViewModel() {


}