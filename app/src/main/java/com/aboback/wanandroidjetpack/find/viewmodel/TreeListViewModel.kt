package com.aboback.wanandroidjetpack.find.viewmodel

import android.app.Application
import com.aboback.base.rv.QuickAdapter
import com.aboback.base.viewmodel.BaseRepositoryViewModel
import com.aboback.wanandroidjetpack.R
import com.aboback.wanandroidjetpack.bean.ItemDatasBean
import com.aboback.wanandroidjetpack.bean.ObjectDataBean
import com.aboback.wanandroidjetpack.collect.ui.CollectContentPage
import com.aboback.wanandroidjetpack.find.TreeListRepository
import com.aboback.wanandroidjetpack.home.viewmodel.ItemHomeVM
import com.aboback.wanandroidjetpack.rv.RecyclerViewVM
import com.aboback.wanandroidjetpack.util.collectDelegate
import com.aboback.wanandroidjetpack.util.launch
import com.aboback.wanandroidjetpack.util.response
import com.aboback.wanandroidjetpack.util.unCollectDelegate
import com.aboback.wanandroidjetpack.viewmodel.TitleViewModel

/**
 * @author jhb
 * @date 2020/11/17
 */
class TreeListViewModel(app: Application) : BaseRepositoryViewModel<TreeListRepository>(app, TreeListRepository()) {
    companion object {
        const val TITLE = "title"
        const val CID = "cid"
    }

    var mTitleVM = TitleViewModel(
            leftAction = {
                finish()
            },
            title = ""
    )

    private var mData = arrayListOf<ItemHomeVM>()
    private val mAdapter = QuickAdapter(R.layout.item_rv_home, mData)

    private var mCurrPage = 0
    private var mPageCount = 1

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
            requestServer(true)
        }
    }

    private var mCid: Int? = null
    override fun onModelBind() {
        super.onModelBind()

        mBundle.getString(TITLE).let {
            mTitleVM.mTitle.set(it)
        }

        mCid = mBundle.getInt(CID)

        requestServer(true)
    }

    private fun requestServer(showDialog: Boolean) {

        launch(showDialog) {
            response(mRepo.articleList(mCurrPage, mCid)) {
                onSuccess(showDialog)
            }
        }


    }


    private fun ObjectDataBean.onSuccess(showDialog: Boolean) {
        if (!showDialog) {
            mData.clear()
        }
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
            onCollectClick = {
                if (mCollect.get()) {
                    mId?.let {
                        unCollectDelegate(it, mRepo)
                    }
                } else {
                    mId?.let {
                        collectDelegate(it, mRepo)
                    }
                }
            }
        })
    }

}