package com.aboback.base.rv

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.aboback.base.VariableId
import com.chad.library.adapter.base.viewholder.BaseViewHolder

/**
 * @author jhb
 * @date 2020/10/27
 */
class QuickViewHolder(private val mBinding: ViewDataBinding) : BaseViewHolder(mBinding.root) {

    companion object {

        fun create(@LayoutRes layoutId: Int, parent: ViewGroup): QuickViewHolder =
                QuickViewHolder(DataBindingUtil.inflate<ViewDataBinding>(LayoutInflater.from(parent.context), layoutId, parent, false))


    }


    fun <T : VariableId> bind(item: T) {
        mBinding.setVariable(item.id(), item)
        mBinding.executePendingBindings()
    }


}