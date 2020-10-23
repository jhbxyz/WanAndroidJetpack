package com.aboback.wanandroidjetpack.base

import android.app.Application
import androidx.databinding.library.baseAdapters.BR
import com.aboback.wanandroidjetpack.base.viewmodel.BaseViewModel

/**
 * Created by jhb on 2020-01-20.
 */
abstract class BaseLayoutViewModel(app: Application) : BaseViewModel(app), VariableIdModel {





    override fun getVariableId(): Int = BR.layout
}