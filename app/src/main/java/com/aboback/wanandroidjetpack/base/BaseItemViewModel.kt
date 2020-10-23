package com.aboback.wanandroidjetpack.base

import android.app.Application
import androidx.databinding.library.baseAdapters.BR
import com.aboback.base.VariableId
import com.aboback.base.viewmodel.BaseViewModel

/**
 * Created by jhb on 2020-01-20.
 */
abstract class BaseItemViewModel(app: Application) : BaseViewModel(app), VariableId {


    override fun id(): Int = BR.item
}