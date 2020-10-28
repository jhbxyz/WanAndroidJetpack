package com.aboback.base.rv

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.aboback.base.VariableId
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter

/**
 * @author jhb
 * @date 2020/10/27
 */
class QuickMultiAdapter<T : BaseMultiItemViewModel>(mData: MutableList<T>) : BaseMultiItemQuickAdapter<T, QuickViewHolder>(mData) {

    override fun createBaseViewHolder(parent: ViewGroup, layoutResId: Int): QuickViewHolder = QuickViewHolder.create(layoutResId, parent)

    override fun convert(holder: QuickViewHolder, item: T) {
        holder.bind(item)
    }

    fun addHeader(context: Context, @LayoutRes layoutId: Int, header: VariableId) {
        val binding = DataBindingUtil.inflate<ViewDataBinding>(LayoutInflater.from(context), layoutId, null, false)
        binding.setVariable(header.id(), header)
        binding.executePendingBindings()
        addHeaderView(binding.root)
    }


}