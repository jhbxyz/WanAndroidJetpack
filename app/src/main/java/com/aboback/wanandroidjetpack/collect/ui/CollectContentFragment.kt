package com.aboback.wanandroidjetpack.collect.ui

import android.app.Application
import com.aboback.base.ui.BaseVMRepositoryFragment
import com.aboback.wanandroidjetpack.R
import com.aboback.wanandroidjetpack.collect.viewmodel.CollectContentVM

/**
 * @author jhb
 * @date 2020/10/27
 */
enum class CollectContentPage {
    COLLECT_ARTICLE, INTERVIEW_RELATE, SHARE_ARTICLE, COLLECT_WEBSITE, SHARE_PROJECT,
}

class CollectContentFragment(private val mContentPage: CollectContentPage) : BaseVMRepositoryFragment<CollectContentVM>(R.layout.fragment_collect_content) {
    override fun getViewModel(app: Application) = CollectContentVM(mContentPage, app)
}