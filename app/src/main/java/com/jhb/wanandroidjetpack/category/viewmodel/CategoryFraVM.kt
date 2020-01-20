package com.jhb.wanandroidjetpack.category.viewmodel

import androidx.databinding.ObservableField
import androidx.recyclerview.widget.LinearLayoutManager
import com.jhb.wanandroidjetpack.R
import com.jhb.wanandroidjetpack.base.BaseViewModel
import com.jhb.wanandroidjetpack.base.BaseItemViewModel
import com.jhb.wanandroidjetpack.base.WanApp
import com.jhb.wanandroidjetpack.rv.BaseRecyclerViewAdapter
import com.jhb.wanandroidjetpack.rv.RecyclerViewVM

/**
 * Created by jhb on 2020-01-19.
 */
class CategoryFraVM : BaseViewModel() {

    var mText = ObservableField(this.javaClass.simpleName)

    var rvVm = RecyclerViewVM().apply {

    }


    private var mDataList = arrayListOf<BaseItemViewModel>()
    private var mAdapter = BaseRecyclerViewAdapter(R.layout.item_rv_category, mDataList)

    fun initRv(){

        rvVm.mLayoutManager = LinearLayoutManager(WanApp.instance)
        for (i in 0..10) {
            mDataList.add(ItemRVCategoryVM("content- sss_$i"))
        }

        rvVm.mAdapterObservable.set(mAdapter)
        mAdapter.notifyDataSetChanged()
    }


    override fun onModelBind() {
        super.onModelBind()


        rvVm.mLayoutManager = LinearLayoutManager(WanApp.instance)
        for (i in 0..10) {
            mDataList.add(ItemRVCategoryVM("content- sss_$i"))
        }

        rvVm.mAdapterObservable.set(mAdapter)
        mAdapter.notifyDataSetChanged()


    }


}