package com.jhb.wanandroidjetpack.base

import androidx.databinding.library.baseAdapters.BR

/**
 * Created by jhb on 2020-01-20.
 */
abstract class BaseItemViewModel : BaseViewModel(), VariableIdModel {


    override fun getVariableId(): Int = BR.item
}