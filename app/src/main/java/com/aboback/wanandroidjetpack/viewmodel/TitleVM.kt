package com.aboback.wanandroidjetpack.viewmodel

import android.app.Application
import android.graphics.Color
import android.graphics.drawable.Drawable
import com.aboback.base.getDrawable
import com.aboback.base.getResColor
import com.aboback.wanandroidjetpack.R

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