package com.aboback.wanandroidjetpack.find.viewmodel

import android.app.Application
import com.aboback.base.rv.QuickAdapter
import com.aboback.base.viewmodel.BaseRepositoryViewModel
import com.aboback.wanandroidjetpack.R
import com.aboback.wanandroidjetpack.find.FindContentRepository
import com.aboback.wanandroidjetpack.find.ui.FindContentPage
import com.aboback.wanandroidjetpack.rv.RecyclerViewVM
import com.aboback.wanandroidjetpack.util.noMoreData

/**
 * Created by jhb on 2020-03-11.
 */
class FindContentVM(private val mContentPage: FindContentPage, app: Application) : BaseRepositoryViewModel<FindContentRepository>(app, FindContentRepository(mContentPage)) {

    var mDataLeft = arrayListOf<ItemFindContentVM>()
    var mDataRight = arrayListOf<ItemFindContentVM>()
    val mAdapterLeft = QuickAdapter(R.layout.item_rv_find_content, mDataLeft)
    val mAdapterRight = QuickAdapter(R.layout.item_rv_find_content, mDataRight)

    private var mCurrPage = 0
    private var mPageCount = 1

    var rvVMLeft = RecyclerViewVM(app).apply {
        mAdapterObservable.set(mAdapterLeft)

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

    var rvVMRight = RecyclerViewVM(app).apply {
        mAdapterObservable.set(mAdapterRight)
    }

    override fun onModelBind() {
        super.onModelBind()

        for (i in 0..50) {
            mDataLeft.add(ItemFindContentVM(getApplication()).apply {
                mContent.set("item = $i")
            })
        }

        for (i in 0..50) {
            mDataRight.add(ItemFindContentVM(getApplication()).apply {
                mContent.set("item  right = $i")
            })
        }

    }


}