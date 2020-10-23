package com.aboback.wanandroidjetpack.viewmodel

import android.app.Application
import android.graphics.drawable.Drawable
import com.aboback.wanandroidjetpack.R
import com.aboback.wanandroidjetpack.util.getDrawable

/**
 * Created by jhb on 2020-01-15.
 */
class TitleVM(app: Application,
              var leftDrawable: Drawable? = R.drawable.abc_vector_test.getDrawable(),
              var leftAction: (() -> Unit)? = null,
              var title: String,
              var rightText: String = "",
              var rightAction: (() -> Unit)? = null

)