package com.aboback.base.rv

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.aboback.base.VariableId
import com.chad.library.adapter.base.BaseQuickAdapter

/**
 * @author jhb
 * @date 2020/10/27
 */
class QuickAdapter<T : BaseItemViewModel>(@LayoutRes var layoutId: Int, mData: MutableList<T>) : BaseQuickAdapter<T, QuickViewHolder>(layoutId, mData) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuickViewHolder {
        val binding = DataBindingUtil.inflate<ViewDataBinding>(LayoutInflater.from(parent.context), layoutId, parent, false)
        return QuickViewHolder(binding)
    }

    override fun convert(holder: QuickViewHolder, item: T) {
        val binding = DataBindingUtil.getBinding<ViewDataBinding>(holder.itemView)
        binding?.setVariable(item.id(), item)
        binding?.executePendingBindings()

    }

    fun addHeader(context: Context, @LayoutRes layoutId: Int, header: VariableId) {
        val binding = DataBindingUtil.inflate<ViewDataBinding>(LayoutInflater.from(context), layoutId, null, false)
        binding.setVariable(header.id(), header)
        binding.executePendingBindings()
        addHeaderView(binding.root)
    }


}