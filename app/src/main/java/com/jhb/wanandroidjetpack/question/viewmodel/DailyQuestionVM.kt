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
import com.jhb.wanandroidjetpack.util.logEWhitT
import com.jhb.wanandroidjetpack.util.subIoObsMain
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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
                t.data.datas.forEach {
                    mData.add(ItemDailyQuestionVM(it))
                }
                mAdapter.notifyDataSetChanged()
            }

            override fun onFinish() {
                super.onFinish()
                hideLoading()
            }
        })

        val wendaList2 = WanService.api.wendaList2(mCurrPage)

        wendaList2.enqueue(object : Callback<WendaListBean> {
            override fun onFailure(call: Call<WendaListBean>, t: Throwable) {


            }

            override fun onResponse(call: Call<WendaListBean>, response: Response<WendaListBean>) {
                response.body()?.toString()?.logEWhitT("wendaList2")
            }

        })

        val wendaList3 = WanService.api.wendaList3(mCurrPage)

        wendaList3.enqueue(object : Callback<ResponseBody> {
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {


            }

            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                response.body()?.string()?.logEWhitT("wendaList3")


            }

        })
    }


}