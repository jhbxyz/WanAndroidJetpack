package com.aboback.wanandroidjetpack.wenda

import android.app.Application
import com.aboback.base.ui.BaseVMRepositoryFragment
import com.aboback.wanandroidjetpack.R
import com.aboback.wanandroidjetpack.main.RvScrollToTop
import com.aboback.wanandroidjetpack.main.ui.MainActivity
import com.aboback.wanandroidjetpack.util.RvScrollDelegate

/**
 * @author jhb
 * @date 2020/10/27
 */
class WenDaFragment : BaseVMRepositoryFragment<WenDaVM>(R.layout.fragment_wenda), RvScrollToTop {

    override fun getViewModel(app: Application) = WenDaVM(app)

    override fun onViewInit() {
        super.onViewInit()
        bindScrollListener()
    }

    override fun bindScrollListener() {
        RvScrollDelegate.bindScrollListener(mActivity as MainActivity, mRealVM.rvVM)
    }

    override fun scrollToTop() {
        RvScrollDelegate.scrollToTop(mRealVM.rvVM)
    }
}