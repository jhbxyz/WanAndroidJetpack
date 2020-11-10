package com.aboback.wanandroidjetpack.find.ui

import android.app.Application
import com.aboback.base.ui.BaseVMRepositoryFragment
import com.aboback.wanandroidjetpack.R
import com.aboback.wanandroidjetpack.collect.SelectPage
import com.aboback.wanandroidjetpack.find.viewmodel.FindContentProjectTreeVM
import com.aboback.wanandroidjetpack.main.RvScrollToTop
import com.aboback.wanandroidjetpack.main.ui.MainActivity
import com.aboback.wanandroidjetpack.util.RvScrollDelegate

/**
 * @author jhb
 * @date 2020/10/27
 */
class FindContentProjectTreeFragment : BaseVMRepositoryFragment<FindContentProjectTreeVM>(R.layout.fragment_find_content_project_tree), RvScrollToTop, SelectPage {


    private var mFragmentInit = false
    private var isTabLayoutClick = false

    override fun getViewModel(app: Application) = FindContentProjectTreeVM(app)

    override fun onViewInit() {
        super.onViewInit()
        mFragmentInit = true
        bindScrollListener()
    }

    override fun onEvent() {
        super.onEvent()
        if (isTabLayoutClick) {
            onSelectPage()
        }
    }

    override fun bindScrollListener() {
        RvScrollDelegate.bindScrollListener(mainActivity = mActivity as MainActivity, rvVM = mRealVM.rvVMRight)
    }

    override fun scrollToTop() {
        RvScrollDelegate.scrollToTop(mRealVM.rvVMRight)
    }

    override fun onSelectPage() {
        if (!mFragmentInit) {
            isTabLayoutClick = true
        } else {
            if (!mRealVM.isRequestSuccess) {
                mRealVM.requestServer()
            }
        }

    }

}