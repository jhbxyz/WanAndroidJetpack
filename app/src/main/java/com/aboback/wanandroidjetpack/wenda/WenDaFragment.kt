package com.aboback.wanandroidjetpack.wenda

import android.app.Application
import androidx.lifecycle.Observer
import com.aboback.base.ui.BaseVMRepositoryFragment
import com.aboback.wanandroidjetpack.R
import com.aboback.wanandroidjetpack.bridge.GlobalSingle
import com.aboback.wanandroidjetpack.main.RvScrollToTop
import com.aboback.wanandroidjetpack.main.ui.MainActivity
import com.aboback.wanandroidjetpack.util.CollectChangeBean
import com.aboback.wanandroidjetpack.util.RvScrollDelegate

/**
 * @author jhb
 * @date 2020/10/27
 */
class WenDaFragment : BaseVMRepositoryFragment<WenDaViewModel>(R.layout.fragment_wenda), RvScrollToTop {

    override fun getViewModel(app: Application) = WenDaViewModel(app)

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