package com.aboback.wanandroidjetpack.find.ui

import android.app.Application
import com.aboback.base.ui.BaseVMRepositoryFragment
import com.aboback.wanandroidjetpack.R
import com.aboback.wanandroidjetpack.collect.SelectPage
import com.aboback.wanandroidjetpack.find.viewmodel.FindContentWeChatVM
import com.aboback.wanandroidjetpack.main.RvScrollToTop

/**
 * @author jhb
 * @date 2020/10/27
 */
class FindContentWeChatFragment : BaseVMRepositoryFragment<FindContentWeChatVM>(R.layout.fragment_find_content_we_chat), RvScrollToTop, SelectPage {

    private var mFragmentInit = false
    private var isTablayoutClick = false

    override fun getViewModel(app: Application) = FindContentWeChatVM(app)

    override fun onViewInit() {
        super.onViewInit()
        mFragmentInit = true
    }

    override fun onEvent() {
        super.onEvent()
        if (isTablayoutClick) {
            onSelectPage()
        }
    }

    override fun bindScrollListener() {
    }

    override fun scrollToTop() {
    }

    override fun onSelectPage() {
        if (!mFragmentInit) {
            isTablayoutClick = true
        } else {
            if (!mRealVM.isRequestSuccess) {
                mRealVM.requestServer()
            }
        }

    }

}