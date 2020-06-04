package com.jhb.wanandroidjetpack.base

import android.app.Application
import androidx.databinding.library.baseAdapters.BR
import com.jhb.wanandroidjetpack.base.viewmodel.BaseViewModel

/**
 * Created by jhb on 2020-01-20.
 */
abstract class BaseItemViewModel(app: Application) : BaseViewModel(app), VariableIdModel {


    override fun getVariableId(): Int = BR.item
}