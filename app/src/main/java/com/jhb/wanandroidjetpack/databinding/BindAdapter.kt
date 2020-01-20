package com.jhb.wanandroidjetpack.databinding

import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

/**
 * Created by jhb on 2020-01-20.
 */

object BindAdapter {

    @JvmStatic
    @BindingAdapter(value = ["onRefreshListener"], requireAll = false)
    fun onRefreshListener(swipeRefreshLayout: SwipeRefreshLayout, listener: (() -> Unit)?) {
        swipeRefreshLayout.setOnRefreshListener {
            listener?.invoke()
        }

    }

}