package com.aboback.wanandroidjetpack.find.viewmodel

import android.app.Application
import com.aboback.base.rv.QuickAdapter
import com.aboback.base.viewmodel.BaseRepositoryViewModel
import com.aboback.wanandroidjetpack.R
import com.aboback.wanandroidjetpack.find.FindContentProjectRepository
import com.aboback.wanandroidjetpack.rv.RecyclerViewVM
import com.aboback.wanandroidjetpack.util.*

/**
 * Created by jhb on 2020-03-11.
 */
class FindContentProjectVM(app: Application) : BaseRepositoryViewModel<FindContentProjectRepository>(app, FindContentProjectRepository()) {

    private var mData = arrayListOf<ItemFindContentProjectVM>()
    private val mAdapter = QuickAdapter(R.layout.item_rv_find_content_project, mData)

    private var mCurrPage = 0
    private var mPageCount = 1
    var isRequestSuccess = false

    var rvVM = RecyclerViewVM(app).apply {
        mRefreshEnable = true
        mAdapterObservable.set(mAdapter)

        mOnRefresh = {
            mIsRefreshing.set(true)

            mCurrPage = 0
            requestServer(true)
        }

        mOnLoadMoreListener = {
            mCurrPage++
            if (mCurrPage < mPageCount) {
                requestServer()
            } else {
                noMoreData()
            }
        }
    }

    fun requestServer(isRefresh: Boolean = false) {
        launch(showDialog = !isRefresh, finish = {
            if (isRefresh) {
                rvVM.mIsRefreshing.set(false)
            }
        }) {
            isRequestSuccess = true
            if (isRefresh) {
                mData.clear()
            }

            val data = mRepo.projectList(mCurrPage).data
            mPageCount = data?.pageCount ?: 1
            data?.datas?.forEach {
                mData.add(ItemFindContentProjectVM(getApplication()).apply {
                    mPath.set(it.envelopePic)
                    mTitle.set(it.title)
                    mDesc.set(it.desc)
                    mTime.set(it.niceShareDate)
                    mAuthor.set(it.author)
                    mCollect.set(it.collect ?: false)
                    mId = it.id
                    onCollectClick = {
                        if (mCollect.get()) {
                            mId?.let { id ->
                                unCollectProjectDelegate(id, mRepo, mData)
                            }
                        } else {
                            mId?.let { id ->
                                collectProjectDelegate(id, mRepo, mData)
                            }
                        }
                    }
                })
            }

            loadSuccess()
            mAdapter.notifyDataSetChanged()

        }
    }


}