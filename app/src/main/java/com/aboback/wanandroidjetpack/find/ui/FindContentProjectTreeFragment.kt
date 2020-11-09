package com.aboback.wanandroidjetpack.find.ui

import android.app.Application
import com.aboback.base.ui.BaseVMRepositoryFragment
import com.aboback.wanandroidjetpack.R
import com.aboback.wanandroidjetpack.collect.SelectPage
import com.aboback.wanandroidjetpack.find.viewmodel.FindContentProjectTreeVM
import com.aboback.wanandroidjetpack.main.RvScrollToTop

/**
 * @author jhb
 * @date 2020/10/27
 */
class FindContentProjectTreeFragment : BaseVMRepositoryFragment<FindContentProjectTreeVM>(R.layout.fragment_find_content_project_tree), RvScrollToTop, SelectPage {


    override fun getViewModel(app: Application) = FindContentProjectTreeVM(app)

    override fun onViewInit() {
        super.onViewInit()
    }

    override fun onEvent() {
        super.onEvent()
    }

    override fun bindScrollListener() {
    }

    override fun scrollToTop() {
    }

    override fun onSelectPage() {

    }

}