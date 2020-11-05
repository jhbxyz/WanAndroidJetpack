package com.aboback.wanandroidjetpack.find.ui

import android.app.Application
import androidx.lifecycle.Observer
import com.aboback.base.ui.BaseVMRepositoryFragment
import com.aboback.wanandroidjetpack.R
import com.aboback.wanandroidjetpack.bridge.GlobalSingle
import com.aboback.wanandroidjetpack.collect.SelectPage
import com.aboback.wanandroidjetpack.collect.viewmodel.CollectContentVM
import com.aboback.wanandroidjetpack.find.viewmodel.FindContentVM
import com.aboback.wanandroidjetpack.main.RvScrollToTop
import com.aboback.wanandroidjetpack.main.ui.MainActivity
import com.aboback.wanandroidjetpack.util.RvScrollDelegate
import java.io.Serializable

/**
 * @author jhb
 * @date 2020/10/27
 */
enum class FindContentPage : Serializable {
    TREE, WE_CHAT, NAVIGATION, PROJECT, PROJECT_CATEGORY,
}

class FindContentFragment(private val mContentPage: FindContentPage) : BaseVMRepositoryFragment<FindContentVM>(R.layout.fragment_find_content), RvScrollToTop, SelectPage {


    override fun getViewModel(app: Application) = FindContentVM(mContentPage, app)

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