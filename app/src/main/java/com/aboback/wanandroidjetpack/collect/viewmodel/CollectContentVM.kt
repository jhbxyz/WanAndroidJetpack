package com.aboback.wanandroidjetpack.collect.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.aboback.base.ItemType
import com.aboback.base.rv.BaseMultiItemViewModel
import com.aboback.base.rv.QuickMultiAdapter
import com.aboback.base.util.logWithTag
import com.aboback.base.util.showToast
import com.aboback.base.viewmodel.BaseRepositoryViewModel
import com.aboback.wanandroidjetpack.R
import com.aboback.wanandroidjetpack.base.WanApp
import com.aboback.wanandroidjetpack.bean.ItemDatasBean
import com.aboback.wanandroidjetpack.bean.ObjectDataBean
import com.aboback.wanandroidjetpack.bridge.GlobalSingle
import com.aboback.wanandroidjetpack.collect.CollectContentRepository
import com.aboback.wanandroidjetpack.collect.ui.CollectContentPage
import com.aboback.wanandroidjetpack.common.EditDialogEvent
import com.aboback.wanandroidjetpack.common.EditDialogEventBean
import com.aboback.wanandroidjetpack.home.viewmodel.ItemHomeVM
import com.aboback.wanandroidjetpack.login.ui.LoginActivity
import com.aboback.wanandroidjetpack.login.viewmodel.LoginViewModel
import com.aboback.wanandroidjetpack.rv.RecyclerViewVM
import com.aboback.wanandroidjetpack.util.*
import com.aboback.wanandroidjetpack.view.EditPage

/**
 * Created by jhb on 2020-03-11.
 */
class CollectContentVM(private val mContentPage: CollectContentPage, app: Application) : BaseRepositoryViewModel<CollectContentRepository>(app, CollectContentRepository(mContentPage)) {

    private var mData = mutableListOf<BaseMultiItemViewModel>()
    private val mAdapter = QuickMultiAdapter(mData).apply {
        addType(R.layout.item_rv_collect_website, ItemType.ITEM_COLLECT_WEBSITE)
        addType(R.layout.item_rv_home, ItemType.ITEM_HOME_MAIN)
    }

    private var mCurrPage = 0
    private var mPageCount = 1

    var isRequestSuccess = false

    var mDeleteShareArticle = MutableLiveData<Boolean>()
    private var mId: Int? = null

    var rvVM = RecyclerViewVM(app).apply {
        mRefreshEnable = (/*mContentPage != CollectContentPage.COLLECT_WEBSITE &&*/ mContentPage != CollectContentPage.SHARE_PROJECT)
        mAdapterObservable.set(mAdapter)

        mOnRefresh = {
            mIsRefreshing.set(true)

            mCurrPage = 0

            requestServer(false)
        }

        mOnLoadMoreListener = {
            mCurrPage++
            if (mCurrPage < mPageCount) {
                requestServer(true)
            } else {
                noMoreData()
            }
        }
    }

    fun updateCollectState(bean: CollectChangeBean) {
        mData.filterIsInstance<ItemHomeVM>().find { it.mId == bean.id }?.mCollect?.set(bean.isCollect)
    }

    fun requestServer(showDialog: Boolean = true) {
        if (!WanApp.isLogin && mContentPage != CollectContentPage.INTERVIEW_RELATE) {
            rvVM.mIsRefreshing.set(false)
            loginFirst()
            startActivity(LoginActivity::class.java, LoginViewModel.COLLECT_CONTENT_PAGE to mContentPage)
            return
        }

        when (mContentPage) {
            CollectContentPage.COLLECT_ARTICLE -> {
                launch(showDialog) {
                    response(mRepo.collectArticle(mCurrPage)) {
                        onSuccess(showDialog)
                    }
                }
            }
            CollectContentPage.INTERVIEW_RELATE -> {
                launch(showDialog) {
                    response(mRepo.interviewRelate(mCurrPage)) {
                        onSuccess(showDialog)
                    }
                }
            }
            CollectContentPage.SHARE_ARTICLE -> {
                launch(showDialog) {
                    response(mRepo.shareArticle(mCurrPage)) {
                        data?.onSuccess(showDialog)
                    }
                }
            }
            CollectContentPage.COLLECT_WEBSITE -> {
                collectWebsite(showDialog)
            }
            CollectContentPage.SHARE_PROJECT -> {

            }
            else -> {
                //Nothing
            }
        }
    }

    private fun ObjectDataBean.onSuccess(showDialog: Boolean) {
        if (!showDialog) {
            mData.clear()
        }
        isRequestSuccess = true
        mPageCount = data?.pageCount ?: 1
        data?.datas?.forEach {
            if (mContentPage == CollectContentPage.COLLECT_ARTICLE) {
                it.collect = true
            }
            bindData(it)
        }
        mAdapter.notifyDataSetChanged()
        if (!showDialog) {
            rvVM.mIsRefreshing.set(false)
        }
    }


    private fun bindData(bean: ItemDatasBean) {
        mData.add(ItemHomeVM(getApplication(), bean).apply {
            mCollectIconShow.set(!(mContentPage == CollectContentPage.SHARE_ARTICLE || mContentPage == CollectContentPage.SHARE_PROJECT))
            bindData()
            onCollectClick = {
                if (mCollect.get()) {
                    mId?.let {
                        unCollectDelegate(it, mRepo, isOnMe = mContentPage == CollectContentPage.COLLECT_ARTICLE, originId = mOriginId)
                    }
                } else {
                    mId?.let {
                        collectDelegate(it, mRepo)
                    }
                }
            }

            if (mContentPage == CollectContentPage.SHARE_ARTICLE) {
                onDelClick = {
                    this@CollectContentVM.mId = mId
                    mDeleteShareArticle.value = true
                }
            }

        })
    }

    fun updateWebsiteChangeItem(bean: EditDialogEventBean) {
        mData.filterIsInstance<ItemCollectWebsiteVM>().find { it.mId == bean.id }
            ?.apply {
                mTitle.set(bean.name)
                mLink.set(bean.link)
            }
    }

    val mDelWebsite = MutableLiveData<Int?>()
    fun collectWebsite(showDialog: Boolean) {
        launch(showDialog) {
            response(mRepo.collectWebsite()) {
                rvVM.mIsRefreshing.set(false)
                mData.clear()
                isRequestSuccess = true
                data?.forEach {
                    mData.add(ItemCollectWebsiteVM(getApplication()).apply {
                        mId = it.id
                        mTitle.set(it.name)
                        mLink.set(it.link)
                        onEdit = {
                            GlobalSingle.showEditDialog.value =
                                    EditDialogEvent(
                                            page = EditPage.WEBSITE,
                                            bean = EditDialogEventBean(mId, mTitle.get(), mLink.get()),
                                            collectContentPage = mContentPage
                                    )
                        }
                        onDel = {
                            mDelWebsite.value = mId
                        }
                    })
                }
                mAdapter.notifyDataSetChanged()
            }
        }
    }

    fun delCollectWebsite(id: Int) {
        launch {
            response(mRepo.delCollectWebsite(id)) {
                val websiteData = mData.filterIsInstance<ItemCollectWebsiteVM>()
                mAdapter.removeAt(websiteData.indexOf(websiteData.find { it.mId == id }))
                "删除完成".showToast()
            }
        }
    }

    fun delMyArticle() {
        launch {
            response(mRepo.delMyArticle(mId)) {
                val itemList = mData.filterIsInstance<ItemHomeVM>()
                mAdapter.removeAt(itemList.indexOf(itemList.find { it.mId == mId }))
                "删除完成".showToast()
            }
        }
    }


}