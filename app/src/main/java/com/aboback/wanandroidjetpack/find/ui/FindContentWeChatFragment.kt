package com.aboback.wanandroidjetpack.find.ui

import android.app.Application
import androidx.lifecycle.Observer
import com.aboback.base.ui.BaseVMRepositoryFragment
import com.aboback.wanandroidjetpack.R
import com.aboback.wanandroidjetpack.bridge.GlobalSingle
import com.aboback.wanandroidjetpack.collect.SelectPage
import com.aboback.wanandroidjetpack.collect.viewmodel.CollectContentVM
import com.aboback.wanandroidjetpack.find.viewmodel.FindContentVM
import com.aboback.wanandroidjetpack.find.viewmodel.FindContentWeChatVM
import com.aboback.wanandroidjetpack.main.RvScrollToTop
import com.aboback.wanandroidjetpack.main.ui.MainActivity
import com.aboback.wanandroidjetpack.util.RvScrollDelegate
import java.io.Serializable

/**
 * @author jhb
 * @date 2020/10/27
 */
class FindContentWeChatFragment : BaseVMRepositoryFragment<FindContentWeChatVM>(R.layout.fragment_find_content_we_chat), RvScrollToTop, SelectPage {


    override fun getViewModel(app: Application) = FindContentWeChatVM(app)

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