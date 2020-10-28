package com.aboback.base.rv

import android.app.Application
import androidx.databinding.library.baseAdapters.BR
import com.aboback.base.VariableId
import com.aboback.base.viewmodel.BaseViewModel

/**
 * @author jhb
 * @date 2020/10/28
 */
open class QuickItemViewModel(app: Application) : BaseViewModel(app), VariableId {


    override fun id(): Int = BR.item
}