package com.jhb.wanandroidjetpack.databinding

import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

/**
 * Created by jhb on 2020-01-21.
 * Tip: 双向绑定
 */
object SwipeRefreshLayoutBindingAdapter {

    // 单向绑定 数据驱动 UI
    @JvmStatic
    @BindingAdapter("app:bind_swipeRefreshLayout_refreshing", requireAll = false)
    fun setSwipeRefreshLayoutRefreshing(swipeRefreshLayout: SwipeRefreshLayout, newValue: Boolean) {
        if (swipeRefreshLayout.isRefreshing != newValue) {
            swipeRefreshLayout.isRefreshing = newValue
        }
    }


    // 用户驱动 LiveData
    @JvmStatic
    @BindingAdapter("app:bind_swipeRefreshLayout_refreshingAttrChanged", requireAll = false)
    fun setSwipeRefreshLayoutListener(swipeRefreshLayout: SwipeRefreshLayout, inverseBindingListener: InverseBindingListener?) {
        swipeRefreshLayout.setOnRefreshListener {
            inverseBindingListener?.onChange()
        }
    }

    // 信使,中间人
    // 每当用户下拉刷新，InverseBindingListener通知DataBinding,
    // LiveData就会从swipeRefreshLayout.isRefreshing得知最新的状态，并进行数据的同步更新
    @JvmStatic
    @InverseBindingAdapter(attribute = "app:bind_swipeRefreshLayout_refreshing", event = "app:bind_swipeRefreshLayout_refreshingAttrChanged")
    fun isSwipeRefreshLayoutRefreshing(swipeRefreshLayout: SwipeRefreshLayout) = swipeRefreshLayout.isRefreshing


}