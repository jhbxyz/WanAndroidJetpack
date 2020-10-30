package com.aboback.wanandroidjetpack.home.ui

import android.app.Application
import com.aboback.base.ui.BaseVMRepositoryFragment
import com.aboback.wanandroidjetpack.R
import com.aboback.wanandroidjetpack.home.viewmodel.HomeVM
import com.aboback.wanandroidjetpack.main.RvScrollToTop
import com.aboback.wanandroidjetpack.main.ui.MainActivity
import com.aboback.wanandroidjetpack.util.RvScrollDelegate

/**
 * Created by jhb on 2020-03-11.
 */
class HomeFragment : BaseVMRepositoryFragment<HomeVM>(R.layout.fragment_home), RvScrollToTop {

    override fun getViewModel(application: Application): HomeVM = HomeVM(application)

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