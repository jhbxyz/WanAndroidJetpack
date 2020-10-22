package com.aboback.viewmodel

import android.app.Application
import com.aboback.BR
import com.aboback.VariableId

/**
 * @author jhb
 * @date 2020/10/21
 */
open class BaseLayoutViewModel(app: Application) : BaseViewModel(app), VariableId {

    override fun id() = BR.layout
}