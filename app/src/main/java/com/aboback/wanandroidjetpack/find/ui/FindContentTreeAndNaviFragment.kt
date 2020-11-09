package com.aboback.wanandroidjetpack.find.ui

import android.app.Application
import com.aboback.base.ui.BaseVMRepositoryFragment
import com.aboback.wanandroidjetpack.R
import com.aboback.wanandroidjetpack.collect.SelectPage
import com.aboback.wanandroidjetpack.find.viewmodel.FindContentTreeAndNaviVM
import com.aboback.wanandroidjetpack.main.RvScrollToTop

/**
 * @author jhb
 * @date 2020/10/27
 */
enum class FindContentTreeAndNaviPage {
    TREE, NAVIGATION
}

class FindContentTreeAndNaviFragment(private val mContentPage: FindContentTreeAndNaviPage) : BaseVMRepositoryFragment<FindContentTreeAndNaviVM>(R.layout.fragment_find_content_tree_and_navi), RvScrollToTop, SelectPage {

    private var mFragmentInit = false
    private var isTabLayoutClick = false
    override fun getViewModel(app: Application) = FindContentTreeAndNaviVM(mContentPage, app)

    override fun onViewInit() {
        super.onViewInit()
        mFragmentInit = true
    }

    override fun onEvent() {
        super.onEvent()
        if (isTabLayoutClick) {
            onSelectPage()
        }
    }

    override fun bindScrollListener() {
    }

    override fun scrollToTop() {
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