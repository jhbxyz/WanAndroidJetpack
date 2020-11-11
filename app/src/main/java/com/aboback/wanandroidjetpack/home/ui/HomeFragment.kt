package com.aboback.wanandroidjetpack.home.ui

import android.app.Application
import androidx.lifecycle.Observer
import com.aboback.base.ui.BaseVMRepositoryFragment
import com.aboback.wanandroidjetpack.R
import com.aboback.wanandroidjetpack.bridge.GlobalSingle
import com.aboback.wanandroidjetpack.home.viewmodel.HomeViewModel
import com.aboback.wanandroidjetpack.main.RvScrollToTop
import com.aboback.wanandroidjetpack.main.ui.MainActivity
import com.aboback.wanandroidjetpack.util.CollectChangeBean
import com.aboback.wanandroidjetpack.util.RvScrollDelegate

/**
 * Created by jhb on 2020-03-11.
 */
class HomeFragment : BaseVMRepositoryFragment<HomeViewModel>(R.layout.fragment_home), RvScrollToTop {

    override fun getViewModel(app: Application): HomeViewModel = HomeViewModel(app)

    override fun onViewInit() {
        super.onViewInit()
        bindScrollListener()
    }

    private val mObserver = Observer<CollectChangeBean> {
        mRealVM.updateCollectState(it)
    }

    override fun onResume() {
        super.onResume()
        GlobalSingle.onCollectChange.observe(this, mObserver)
    }

    override fun onPause() {
        super.onPause()
        GlobalSingle.onCollectChange.removeObserver(mObserver)
    }



    override fun bindScrollListener() {
        RvScrollDelegate.bindScrollListener(mActivity as MainActivity, mRealVM.rvVM)
    }

    override fun scrollToTop() {
        RvScrollDelegate.scrollToTop(mRealVM.rvVM)
    }

}