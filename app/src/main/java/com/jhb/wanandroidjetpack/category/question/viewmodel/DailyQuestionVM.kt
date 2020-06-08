package com.jhb.wanandroidjetpack.category.question.viewmodel

import android.app.Application
import androidx.databinding.ViewDataBinding
import com.jhb.wanandroidjetpack.R
import com.jhb.wanandroidjetpack.base.BaseLayoutViewModel
import com.jhb.wanandroidjetpack.base.X5WebActivity
import com.jhb.wanandroidjetpack.bean.WendaListBean
import com.jhb.wanandroidjetpack.db.WenDaListManger
import com.jhb.wanandroidjetpack.net.WanService
import com.jhb.wanandroidjetpack.net.WanSubscriber
import com.jhb.wanandroidjetpack.rv.BaseRecyclerViewAdapter
import com.jhb.wanandroidjetpack.rv.RecyclerViewVM
import com.jhb.wanandroidjetpack.util.logE
import io.reactivex.android.schedulers.AndroidSchedulers

/**
 * Created by jhb on 2020-03-12.
 */
class DailyQuestionVM(app: Application) : BaseLayoutViewModel(app) {

    var mData = arrayListOf<ItemDailyQuestionVM>()
    val mAdapter = BaseRecyclerViewAdapter(R.layout.item_rv_daily_question, mData)

    var rvVm = RecyclerViewVM(app).apply {
        mRefreshEnable = false
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


        mAdapter.setOnItemClickListener(object : BaseRecyclerViewAdapter.OnItemClickListener {
            override fun onItemClick(binding: ViewDataBinding, position: Int) {
                X5WebActivity.startActivity(mData[position].mBean.get()?.link)
                "文章 = ${mData[position].mBean.get()?.title}   id = ${mData[position].mBean.get()?.id}".logE()
            }
        })
    }


    private var mCurrPage = 1
    private val oneDay = 1000 * 60 * 60 * 24
    private fun wendaList() {

        WenDaListManger.getDataBean {
            if (it != null && (System.currentTimeMillis() - it.mLastRequestTime) < 3 * oneDay) {
                bindData(it)
                return@getDataBean
            }
            requestNet()
        }

    }

    private fun requestNet() {
        WanService.api.wendaList(mCurrPage)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : WanSubscriber<WendaListBean>(this@DailyQuestionVM) {
                override fun onSuccess(t: WendaListBean) {

                    t.data?.apply {
                        mLastRequestTime = System.currentTimeMillis()
                        WenDaListManger.insertDataBean(this)
                        bindData(this)
                    }

                }
            })
            .addToDisposable()
    }

    /**
     *  todo tips:过滤掉 赞 < 3的 文章
     */
    private val filterIds = arrayListOf<Int>().apply {
        add(13701)
        add(13347)
        add(12773)
        add(9286)
        add(8857)
        add(8205)
        add(8435)
    }

    private fun bindData(t: WendaListBean.DataBean) {

        t.datas?.forEach {
            if (!filterIds.contains(it.id) && it.zan ?: 4 > 3) {
                mData.add(ItemDailyQuestionVM(getApplication()).apply {
                    mBean.set(it)
                })
            }
        }

        mAdapter.notifyDataSetChanged()
    }


}