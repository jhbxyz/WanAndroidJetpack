package com.jhb.wanandroidjetpack.category.viewmodel

import androidx.databinding.Observable
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.recyclerview.widget.LinearLayoutManager
import com.jhb.wanandroidjetpack.BR
import com.jhb.wanandroidjetpack.R
import com.jhb.wanandroidjetpack.base.BaseViewModel
import com.jhb.wanandroidjetpack.base.BaseItemViewModel
import com.jhb.wanandroidjetpack.base.BaseLayoutViewModel
import com.jhb.wanandroidjetpack.base.WanApp
import com.jhb.wanandroidjetpack.rv.BaseRecyclerViewAdapter
import com.jhb.wanandroidjetpack.rv.RecyclerViewVM
import com.jhb.wanandroidjetpack.util.logE

/**
 * Created by jhb on 2020-01-19.
 */
class CategoryFraVM : BaseLayoutViewModel() {

    var mText = ObservableField(this.javaClass.simpleName)


    private var mDataList = arrayListOf<BaseItemViewModel>()
    private var mAdapter = BaseRecyclerViewAdapter(R.layout.item_rv_category, mDataList)

    var rvVm = RecyclerViewVM().apply {
        mAdapterObservable.set(mAdapter)
        mRefreshEnable = true

        mOnRefresh = {
            mIsRefreshing.set(true)
            onRefresh()
        }

        mOnLoadMoreListener = {
            onLoadMore()
        }
    }


    private fun onRefresh() {

        mDataList.clear()
        for (i in 0..10) {
            mDataList.add(ItemRVCategoryVM("刷新的 ontent- sss_$i"))
        }

        rvVm.mIsRefreshing.set(false)
        mAdapter.notifyDataSetChanged()

    }

    private fun onLoadMore() {

        for (i in 0..10) {
            mDataList.add(ItemRVCategoryVM("加载更多  ontent- sss_$i"))
        }

        mAdapter.notifyDataSetChanged()

    }


    private fun initRv() {
        for (i in 0..39) {
            mDataList.add(ItemRVCategoryVM("content- sss_$i"))
        }

        mAdapter.notifyDataSetChanged()
    }

    override fun onModelBind() {
        super.onModelBind()
        initRv()


    }

    override fun getVariableId(): Int = BR.layout


}