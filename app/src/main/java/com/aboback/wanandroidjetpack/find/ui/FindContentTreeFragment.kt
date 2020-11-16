package com.aboback.wanandroidjetpack.find.ui

import android.app.Application
import androidx.lifecycle.Observer
import com.aboback.base.ui.BaseVMRepositoryFragment
import com.aboback.base.util.logWithTag
import com.aboback.wanandroidjetpack.R
import com.aboback.wanandroidjetpack.bridge.GlobalSingle
import com.aboback.wanandroidjetpack.collect.SelectPage
import com.aboback.wanandroidjetpack.find.viewmodel.FindContentTreeVM

/**
 * @author jhb
 * @date 2020/10/27
 */
class FindContentTreeFragment : BaseVMRepositoryFragment<FindContentTreeVM>(R.layout.fragment_find_content_tree), SelectPage {

    private var mFragmentInit = false
    private var isTabLayoutClick = false
    override fun getViewModel(app: Application) = FindContentTreeVM(app)

    override fun onViewInit() {
        super.onViewInit()
        mFragmentInit = true
        "this = $this   onViewInit   mFragmentInit = $mFragmentInit".logWithTag(mTag)
    }

    override fun onEvent() {
        super.onEvent()
        if (isTabLayoutClick) {
            "this = $this  onEvent  mFragmentInit = $mFragmentInit".logWithTag(mTag)

            onSelectPage()
        }

        register()

    }

    override fun pageIndex() = 0

    override fun onSelectPage() {
        if (!mFragmentInit) {
            isTabLayoutClick = true
            "this = $this  onSelectPage  mFragmentInit = $mFragmentInit".logWithTag(mTag)

        } else {
            if (!mRealVM.isRequestSuccess) {
                "this = $this  requestServer  mFragmentInit = $mFragmentInit".logWithTag(mTag)

                mRealVM.requestServer()
            }
        }
    }

}