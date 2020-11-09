package com.aboback.wanandroidjetpack.find.viewmodel

import android.app.Application
import com.aboback.base.rv.QuickAdapter
import com.aboback.base.viewmodel.BaseRepositoryViewModel
import com.aboback.wanandroidjetpack.R
import com.aboback.wanandroidjetpack.find.FindContentProjectRepository
import com.aboback.wanandroidjetpack.rv.RecyclerViewVM
import com.aboback.wanandroidjetpack.util.launch
import com.aboback.wanandroidjetpack.util.noMoreData

/**
 * Created by jhb on 2020-03-11.
 */
class FindContentProjectVM(app: Application) : BaseRepositoryViewModel<FindContentProjectRepository>(app, FindContentProjectRepository()) {

    private var mData = arrayListOf<ItemFindContentProjectVM>()
    private val mAdapter = QuickAdapter(R.layout.item_rv_find_content_project, mData)

    private var mCurrPage = 0
    private var mPageCount = 1

    var rvVMRight = RecyclerViewVM(app).apply {
        mAdapterObservable.set(mAdapter)

        mOnRefresh = {
            mIsRefreshing.set(true)

            mCurrPage = 0

        }

        mOnLoadMoreListener = {
            mCurrPage++
            if (mCurrPage < mPageCount) {

            } else {
                noMoreData()
            }
        }
    }

    override fun onModelBind() {
        super.onModelBind()

        requestServer()
    }

    fun requestServer() {
        launch {
            mRepo.projectList(mCurrPage).data?.datas?.forEach {
                mData.add(ItemFindContentProjectVM(getApplication()).apply {
                    mPath.set(it.envelopePic)
                    mTitle.set(it.title)
                    mDesc.set(it.desc)
                    mTime.set(it.niceShareDate)
                    mAuthor.set(it.author)
                })
            }

            mAdapter.notifyDataSetChanged()

        }
    }


}