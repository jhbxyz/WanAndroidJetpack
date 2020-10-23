package com.aboback.wanandroidjetpack.viewmodel

import android.app.Application
import android.graphics.Color
import android.graphics.drawable.Drawable
import com.aboback.wanandroidjetpack.R
import com.aboback.wanandroidjetpack.util.getDrawable
import com.aboback.wanandroidjetpack.util.getResColor

/**
 * Created by jhb on 2020-01-15.
 */
class TitleVM(app: Application,
              var leftText: String? = "",
              var leftDrawable: Drawable? = R.drawable.abc_vector_test.getDrawable(),
              var leftAction: (() -> Unit)? = null,
              var title: String = "",
              var rightText: String = "",
              var rightDrawable: Drawable? = null,
              var rightAction: (() -> Unit)? = null,
              var background: Int = R.color.colorAccent.getResColor()

)