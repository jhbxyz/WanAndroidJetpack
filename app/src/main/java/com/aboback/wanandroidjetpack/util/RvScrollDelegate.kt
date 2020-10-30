package com.aboback.wanandroidjetpack.util

import androidx.recyclerview.widget.RecyclerView
import com.aboback.base.util.falsely
import com.aboback.base.util.log
import com.aboback.base.util.logWithTag
import com.aboback.base.util.truely
import com.aboback.wanandroidjetpack.main.ui.MainActivity
import com.aboback.wanandroidjetpack.rv.RecyclerViewVM
import com.aboback.wanandroidjetpack.rv.RvScrollListener

/**
 * @author jhb
 * @date 2020/10/30
 */
object RvScrollDelegate {

    private const val OFFSET_DISTANCE = 10

    fun bindScrollListener(mainActivity: MainActivity, rvVM: RecyclerViewVM) {
        val mFabVisible = mainActivity.mRealVM.mFabVisible

        rvVM.mOnScrollListener.set(object : RvScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                "onScrolled  newState = $newState".log()
                // -1 表示向上滚动, 返回值 true 表示 还可以往上滑动
                if (!recyclerView.canScrollVertically(-1)) {
                    rvVM.mScrollToTop.set(false)
                    mFabVisible.set(false)
                }
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                "onScrolled     dy = $dy".logWithTag("RvScrollUtil")
                if (dy < -OFFSET_DISTANCE && mFabVisible.get().falsely()) {
                    mFabVisible.set(true)
                } else if (dy > OFFSET_DISTANCE && mFabVisible.get().truely()) {
                    mFabVisible.set(false)
                }
            }
        })
    }

    fun scrollToTop(rvVM: RecyclerViewVM) {
        rvVM.mScrollToTop.set(true)
    }

}