package com.jhb.wanandroidjetpack.question.viewmodel

import androidx.databinding.ViewDataBinding
import com.jhb.wanandroidjetpack.R
import com.jhb.wanandroidjetpack.base.BaseLayoutViewModel
import com.jhb.wanandroidjetpack.base.WanWebActivity
import com.jhb.wanandroidjetpack.bean.WendaListBean
import com.jhb.wanandroidjetpack.net.WanObserver
import com.jhb.wanandroidjetpack.net.WanService
import com.jhb.wanandroidjetpack.rv.BaseRecyclerViewAdapter
import com.jhb.wanandroidjetpack.rv.RecyclerViewVM
import com.jhb.wanandroidjetpack.util.subIoObsMain

/**
 * Created by jhb on 2020-03-12.
 */
class DailyQuestionVM : BaseLayoutViewModel() {

    var mData = arrayListOf<ItemDailyQuestionVM>()
    val mAdapter = BaseRecyclerViewAdapter(R.layout.item_rv_daily_question, mData)

    var rvVm = RecyclerViewVM().apply {
        mRefreshEnable = true
        mAdapterObservable.set(mAdapter)

        mOnRefresh = {
            mIsRefreshing.set(true)

            mData.clear()
            mCurrPage = 1
            wendaList()

            mIsRefreshing.set(false)
        }

        mOnLoadMoreListener = {
            mCurrPage++
            wendaList()

        }
    }

    override fun onModelBind() {
        super.onModelBind()

        wendaList()

    }


    override fun onEvent() {
        super.onEvent()

        mAdapter.setOnItemClickListener(object : BaseRecyclerViewAdapter.OnItemClickListener {
            override fun onItemClick(binding: ViewDataBinding, position: Int) {
                WanWebActivity.startActivity(mData[position].mBean.get()?.link)
            }
        })


    }

    private var mCurrPage = 1
    private fun wendaList() {
        showLoading()
        WanService.api.wendaList(mCurrPage).subIoObsMain(object : WanObserver<WendaListBean>() {
            override fun onSuccess(t: WendaListBean) {
                hideLoading()
                t.data.datas.forEach {
                    mData.add(ItemDailyQuestionVM(it))
                }
                mAdapter.notifyDataSetChanged()
            }
        })
    }


}