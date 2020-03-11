package com.jhb.wanandroidjetpack.base

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment

/**
 * Created by jhb on 2020-03-10.
 * 懒加载的 Fragment ***已过时****
 * Androidx 1.1.0版本中，Google对于Fragment进行了优化处理，使得延迟加载也有了新的解决方案。
 */
@Deprecated("已过时的方式 () Deprecated 这个 setUserVisibleHint 方法")
abstract class BaseLazyFragment : Fragment() {

    // 当前Fragment状态是否可见
    private var isVisibleToUser = false
    // 是否已创建View
    private var isViewCreated = false
    // 是否第一次加载数据
    private var isFirstLoad = true

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        this.isVisibleToUser = isVisibleToUser
        onLazyLoad()
    }

    private fun onLazyLoad() {
        if (isVisibleToUser && isViewCreated && isFirstLoad) {
            isFirstLoad = false
            lazyLoad()
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        isViewCreated = true
        onLazyLoad()
    }

    protected abstract fun lazyLoad()


}