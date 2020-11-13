package com.aboback.wanandroidjetpack.view

import android.app.Application
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import com.aboback.base.util.isNull
import com.aboback.base.util.showToast
import com.aboback.base.viewmodel.BaseLayoutViewModel
import com.aboback.wanandroidjetpack.bridge.GlobalSingle
import com.aboback.wanandroidjetpack.collect.ui.CollectContentPage
import com.aboback.wanandroidjetpack.common.EditDialogEvent
import com.aboback.wanandroidjetpack.common.EditDialogEventBean
import com.aboback.wanandroidjetpack.network.WanServer
import com.aboback.wanandroidjetpack.util.launch
import com.aboback.wanandroidjetpack.util.response

/**
 * @author jhb
 * @date 2020/11/12
 */
class EditDialogViewModel(app: Application) : BaseLayoutViewModel(app) {

    var mEditName = ObservableField("")

    var mTitle = ObservableField("")
    var mTitleHint = ObservableField("")

    var mAuthor = ObservableField("")
    var mAuthorHint = ObservableField("")
    var mAuthorVisible = ObservableField(true)

    var mLink = ObservableField("")
    var mLinkHint = ObservableField("")

    var mId: Int? = null
    var mCollectContentPage: CollectContentPage = CollectContentPage.COLLECT_ARTICLE
    private var mCurrPage = EditPage.COLLECT_ARTICLE

    fun handlePageData(page: EditPage, bean: EditDialogEventBean? = null, collectContentPage: CollectContentPage) {
        mCurrPage = page
        mCollectContentPage = collectContentPage
        when (page) {
            EditPage.COLLECT_ARTICLE -> {
                mEditName.set("收藏文章")
                mTitleHint.set("*请输入标题")
                mAuthorHint.set("请输入作者")
                mLinkHint.set("*请输入链接地址")
                mAuthorVisible.set(true)
                mTitle.set(bean?.name)
                mLink.set(bean?.link)
            }
            EditPage.WEBSITE -> {
                mEditName.set(if (bean?.id.isNull()) "收藏网站" else "编辑网站")
                mTitleHint.set("*请输入网站名称")
                mLinkHint.set("*请输入链接地址")
                mAuthorVisible.set(false)
                mTitle.set(bean?.name)
                mLink.set(bean?.link)
                mId = bean?.id
            }
            EditPage.SHARE_ARTICLE -> {
                mEditName.set("分享文章")
                mTitleHint.set("*请输入标题")


            }
            else -> {
                //Nothing
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
                if (mId.isNull()) {
                    collectWebsite()
                } else {
                    updateCollectWebsite()
                }
            }
            EditPage.SHARE_ARTICLE -> {

            }
        }

    }

    private fun resetFields() {
        mTitle.set("")
        mAuthor.set("")
        mLink.set("")
    }

    private fun collectAdd() {
        launch {
            response(WanServer.api.collectAdd(mTitle.get(), mAuthor.get(), mLink.get())) {
                GlobalSingle.showEditDialog.value = EditDialogEvent(page = EditPage.NONE, collectContentPage = mCollectContentPage)
                GlobalSingle.onAddCollectArticle.value = mCollectContentPage

                resetFields()
                "收藏文章成功...".showToast()
            }
        }
    }

    private fun collectWebsite() {
        launch {
            response(WanServer.api.addCollectWebsite(name = mTitle.get(), link = mLink.get())) {
                GlobalSingle.showEditDialog.value = EditDialogEvent(page = EditPage.NONE, collectContentPage = mCollectContentPage)
                GlobalSingle.onAddCollectWebsite.value = true

                resetFields()
                "收藏网站成功...".showToast()
            }
        }
    }

    private fun updateCollectWebsite() {
        launch {
            response(WanServer.api.updateCollectWebsite(id = mId, name = mTitle.get(), link = mLink.get())) {
                GlobalSingle.showEditDialog.value = EditDialogEvent(
                        collectContentPage = mCollectContentPage,
                        bean = EditDialogEventBean(mId, mTitle.get(), mLink.get()), page = EditPage.NONE)
                resetFields()
                "更新网站成功...".showToast()
            }
        }
    }


    private fun addMyArticle() {
        launch {
            response(WanServer.api.addMyArticle(title = mTitle.get(), author = mAuthor.get(), link = mLink.get())) {
                GlobalSingle.showEditDialog.value = EditDialogEvent(page = EditPage.NONE, collectContentPage = mCollectContentPage)
                GlobalSingle.onAddCollectWebsite.value = true
                resetFields()
                "收藏网站成功...".showToast()

            }
        }
    }


}