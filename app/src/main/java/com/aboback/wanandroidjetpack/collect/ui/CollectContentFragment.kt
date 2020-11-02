package com.aboback.wanandroidjetpack.collect.ui

import android.app.Application
import com.aboback.base.ui.BaseVMRepositoryFragment
import com.aboback.wanandroidjetpack.R
import com.aboback.wanandroidjetpack.collect.viewmodel.CollectContentVM
import com.aboback.wanandroidjetpack.main.RvScrollToTop
import com.aboback.wanandroidjetpack.main.ui.MainActivity
import com.aboback.wanandroidjetpack.util.RvScrollDelegate

/**
 * @author jhb
 * @date 2020/10/27
 */
enum class CollectContentPage {
    COLLECT_ARTICLE, INTERVIEW_RELATE, SHARE_ARTICLE, COLLECT_WEBSITE, SHARE_PROJECT,
}

class CollectContentFragment(private val mContentPage: CollectContentPage) : BaseVMRepositoryFragment<CollectContentVM>(R.layout.fragment_collect_content), RvScrollToTop {

    override fun getViewModel(app: Application) = CollectContentVM(mContentPage, app)

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