package com.aboback.base.rv

import com.aboback.base.BR
import com.aboback.base.VariableId
import com.chad.library.adapter.base.entity.MultiItemEntity

/**
 * @author jhb
 * @date 2020/10/27
 */
abstract class BaseMultItemViewModel : VariableId, MultiItemEntity {

    override fun id(): Int = BR.item
}