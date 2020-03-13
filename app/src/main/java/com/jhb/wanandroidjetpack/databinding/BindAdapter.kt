package com.jhb.wanandroidjetpack.databinding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.jhb.wanandroidjetpack.util.logE

/**
 * Created by jhb on 2020-01-20.
 */

object BindAdapter {

    @JvmStatic
    @BindingAdapter(
            value = ["onRefreshListener"],
            requireAll = false
    )
    fun onRefreshListener(swipeRefreshLayout: SwipeRefreshLayout, listener: (() -> Unit)?) {
        swipeRefreshLayout.setOnRefreshListener {
            listener?.invoke()
        }

    }

    @JvmStatic
    @BindingAdapter(value = ["setLoadMoreListener"])
    fun setLoadMoreListener(recyclerView: RecyclerView, listener: () -> Unit) {

        var isToTop = false
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

                val lm = recyclerView.layoutManager as LinearLayoutManager

                val lastVisibleItemPosition = lm.findLastVisibleItemPosition()
                val totalItemCount = recyclerView.adapter?.itemCount ?: 0
                val visibleChildCount = recyclerView.childCount


                if (
                        isToTop &&
                        newState == RecyclerView.SCROLL_STATE_IDLE &&
                        totalItemCount != 0 &&
                        lastVisibleItemPosition == totalItemCount - 1 &&
                        visibleChildCount != 0

                ) {

                    listener.invoke()
                }


            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                isToTop = dy > 0
            }

        })

    }


}