package com.aboback.base.viewmodel

import android.app.Application
import com.aboback.base.BR
import com.aboback.base.VariableId

/**
 * @author jhb
 * @date 2020/10/21
 */
open class BaseLayoutViewModel(app: Application) : BaseViewModel(app), VariableId {

    override fun id() = BR.layout
}