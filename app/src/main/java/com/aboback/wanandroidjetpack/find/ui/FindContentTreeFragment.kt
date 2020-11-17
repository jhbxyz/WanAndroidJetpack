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
    }

    override fun onEvent() {
        super.onEvent()
        if (isTabLayoutClick) {
            onSelectPage()
        }

        register()

    }

    override fun pageIndex() = 0

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