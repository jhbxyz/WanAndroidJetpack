package com.aboback.wanandroidjetpack.view

import android.app.Application
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import com.aboback.base.util.showToast
import com.aboback.base.viewmodel.BaseLayoutViewModel
import com.aboback.wanandroidjetpack.bridge.GlobalSingle
import com.aboback.wanandroidjetpack.network.WanServer
import com.aboback.wanandroidjetpack.util.launch
import com.aboback.wanandroidjetpack.util.response

/**
 * @author jhb
 * @date 2020/11/12
 */
class EditDialogViewModel(app: Application) : BaseLayoutViewModel(app) {

    var mDismisDialog = MutableLiveData<Boolean>()
    var mEditName = ObservableField("")

    var mTitle = ObservableField("")
    var mTitleHint = ObservableField("")

    var mAuthor = ObservableField("")
    var mAuthorHint = ObservableField("")
    var mAuthorVisible = ObservableField(true)

    var mLink = ObservableField("")
    var mLinkHint = ObservableField("")

    private var mCurrPage = EditPage.COLLECT_ARTICLE
    fun handlePageData(page: EditPage) {
        mCurrPage = page
        when (page) {
            EditPage.COLLECT_ARTICLE -> {
                mEditName.set("收藏文章")
                mTitleHint.set("*请输入标题")
                mAuthorHint.set("请输入作者")
                mLinkHint.set("*请输入链接地址")
            }
            EditPage.WEBSITE -> {
                mEditName.set("收藏网站")
                mTitleHint.set("*请输入网站名称")
                mLinkHint.set("*请输入链接地址")
                mAuthorVisible.set(false)
            }
            EditPage.SHARE_ARTICLE -> {
                mEditName.set("分享文章")
                mTitleHint.set("*请输入标题")


            }
        }
    }


    fun onConfirm() {

        if (mTitle.get().isNullOrEmpty()) {
            mTitleHint.get()?.showToast()
            return
        }

        if (mLink.get().isNullOrEmpty()) {
            mLinkHint.get()?.showToast()
            return
        }

        when (mCurrPage) {
            EditPage.COLLECT_ARTICLE -> {
                collectAdd()
            }
            EditPage.WEBSITE -> {
                collectWebsite()
            }
            EditPage.SHARE_ARTICLE -> {

            }
        }

    }

    private fun collectAdd() {
        launch {
            response(WanServer.api.collectAdd(mTitle.get(), mAuthor.get(), mLink.get())) {

            }
        }
    }

    private fun collectWebsite() {
        launch {
            response(WanServer.api.addCollectWebsite(name = mTitle.get(), link = mLink.get())) {
                mTitle.set("")
                mLink.set("")
                "收藏网站成功...".showToast()
                mDismisDialog.value = true
                GlobalSingle.onAddCollectWebsite.value = true
            }
        }
    }
}