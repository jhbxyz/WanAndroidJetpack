package com.aboback.wanandroidjetpack.collect.ui

import android.app.Application
import androidx.lifecycle.Observer
import com.aboback.base.ui.BaseVMRepositoryFragment
import com.aboback.wanandroidjetpack.R
import com.aboback.wanandroidjetpack.bridge.GlobalSingle
import com.aboback.wanandroidjetpack.collect.SelectPage
import com.aboback.wanandroidjetpack.collect.viewmodel.CollectContentVM
import com.aboback.wanandroidjetpack.main.RvScrollToTop
import com.aboback.wanandroidjetpack.main.ui.MainActivity
import com.aboback.wanandroidjetpack.util.RvScrollDelegate
import java.io.Serializable

/**
 * @author jhb
 * @date 2020/10/27
 */
enum class CollectContentPage : Serializable {
    COLLECT_ARTICLE, INTERVIEW_RELATE, SHARE_ARTICLE, COLLECT_WEBSITE, SHARE_PROJECT, NONE
}

class CollectContentFragment(private val mContentPage: CollectContentPage) : BaseVMRepositoryFragment<CollectContentVM>(R.layout.fragment_collect_content), RvScrollToTop, SelectPage {

    private var mFragmentInit = false
    private var isTabLayoutClick = false

    override fun getViewModel(app: Application) = CollectContentVM(mContentPage, app)

    override fun onViewInit() {
        super.onViewInit()
        mFragmentInit = true
        bindScrollListener()
    }

    override fun onEvent() {
        super.onEvent()
        GlobalSingle.isLoginSuccessToCollect.observe(this, Observer {
            if (it == mContentPage) {
                mRealVM.requestServer()
                GlobalSingle.isLoginSuccessToCollect.value = CollectContentPage.NONE
            }
        })
        if (isTabLayoutClick) {
            onSelectPage()
        }
    }

    override fun bindScrollListener() {
        RvScrollDelegate.bindScrollListener(mActivity as MainActivity, mRealVM.rvVM)
    }

    override fun scrollToTop() {
        RvScrollDelegate.scrollToTop(mRealVM.rvVM)
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