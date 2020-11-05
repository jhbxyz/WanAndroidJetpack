package com.aboback.wanandroidjetpack.collect.viewmodel

import android.app.Application
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.aboback.base.rv.QuickAdapter
import com.aboback.base.util.log
import com.aboback.base.util.logWithTag
import com.aboback.base.util.showToast
import com.aboback.base.viewmodel.BaseRepositoryViewModel
import com.aboback.wanandroidjetpack.R
import com.aboback.wanandroidjetpack.base.WanApp
import com.aboback.wanandroidjetpack.bean.ItemDatasBean
import com.aboback.wanandroidjetpack.bean.ObjectDataBean
import com.aboback.wanandroidjetpack.collect.CollectContentRepository
import com.aboback.wanandroidjetpack.collect.ui.CollectContentPage
import com.aboback.wanandroidjetpack.home.viewmodel.ItemHomeVM
import com.aboback.wanandroidjetpack.login.ui.LoginActivity
import com.aboback.wanandroidjetpack.login.viewmodel.LoginViewModel
import com.aboback.wanandroidjetpack.network.WanServer
import com.aboback.wanandroidjetpack.rv.RecyclerViewVM
import com.aboback.wanandroidjetpack.util.launch
import com.aboback.wanandroidjetpack.util.loginFirst
import com.aboback.wanandroidjetpack.util.noMoreData
import com.aboback.wanandroidjetpack.util.response
import com.aboback.wanandroidjetpack.viewmodel.TagViewModel
import kotlinx.coroutines.launch

/**
 * Created by jhb on 2020-03-11.
 */
class CollectContentVM(private val mContentPage: CollectContentPage, app: Application) : BaseRepositoryViewModel<CollectContentRepository>(app, CollectContentRepository(mContentPage)) {

    var mData = arrayListOf<ItemHomeVM>()
    val mAdapter = QuickAdapter(R.layout.item_rv_home, mData)

    private var mCurrPage = 0
    private var mPageCount = 1

    var mSelectCurrentPage = MutableLiveData<CollectContentPage>()

    var isRequestSuccess = false

    var rvVM = RecyclerViewVM(app).apply {
        mRefreshEnable = true
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


    override fun onModelBind() {
        super.onModelBind()

    }

    fun requestServer(showDialog: Boolean = true) {
        if (!WanApp.isLogin) {
            rvVM.mIsRefreshing.set(false)
            loginFirst()
            startActivity(LoginActivity::class.java, LoginViewModel.COLLECT_CONTENT_PAGE to mContentPage)
            return
        }
        launch(showDialog) {
            when (mContentPage) {
                CollectContentPage.COLLECT_ARTICLE -> {
                    mRepo.collectArticle(mCurrPage).onSuccess(showDialog)
                }
                CollectContentPage.INTERVIEW_RELATE -> {
                    mRepo.interviewRelate(mCurrPage).onSuccess(showDialog)

                }
                CollectContentPage.SHARE_ARTICLE -> {
                    mRepo.shareArticle(mCurrPage).data?.onSuccess(showDialog)
                }
                CollectContentPage.COLLECT_WEBSITE -> {
                    mRepo.collectWebsite().data?.forEach {
                        mData.add(ItemHomeVM(getApplication(), it).apply {
                            mTime.set(it.name)
                            mTitle.set(it.link)
                        })
                    }
                    mAdapter.notifyDataSetChanged()
                }
                CollectContentPage.SHARE_PROJECT -> {

                }
            }
        }
    }

    private fun ObjectDataBean.onSuccess(showDialog: Boolean) {
        isRequestSuccess = true
        mPageCount = data?.pageCount ?: 1
        data?.datas?.forEach {
            bindData(it)
        }
        mAdapter.notifyDataSetChanged()
        if (!showDialog) {
            rvVM.mIsRefreshing.set(false)
        }
    }


    private fun bindData(bean: ItemDatasBean) {
        mData.add(ItemHomeVM(getApplication(), bean).apply {
            bindData()

            bean.tags?.forEach { tags ->
                mTagVMList.add(TagViewModel().apply {
                    mContent.set(tags.name)
                })
            }
        })
    }


}