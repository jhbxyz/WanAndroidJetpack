package com.aboback.wanandroidjetpack.category.interview_related.viewmodel

import android.app.Application
import androidx.databinding.ViewDataBinding
import com.aboback.wanandroidjetpack.R
import com.aboback.wanandroidjetpack.base.BaseLayoutViewModel
import com.aboback.wanandroidjetpack.base.X5WebActivity
import com.aboback.wanandroidjetpack.bean.ArticleListBean
import com.aboback.network.WanService
import com.aboback.network.WanSubscriber
import com.aboback.wanandroidjetpack.rv.BaseRecyclerViewAdapter
import com.aboback.wanandroidjetpack.rv.RecyclerViewVM
import io.reactivex.android.schedulers.AndroidSchedulers

/**
 * Created by jhb on 2020-03-12.
 */
class InterviewRelatedVM(app: Application) : BaseLayoutViewModel(app) {

    var mData = arrayListOf<ItemInterviewRelatedVM>()
    val mAdapter = BaseRecyclerViewAdapter(R.layout.item_rv_interview_related, mData)
    private var mCurrPage = 1

    var rvVm = RecyclerViewVM(app).apply {
        mRefreshEnable = false
        mAdapterObservable.set(mAdapter)

        mOnLoadMoreListener = {
            mCurrPage++
            articleList()

        }
    }

    override fun onModelBind() {
        super.onModelBind()

        articleList()


        mAdapter.setOnItemClickListener(object : BaseRecyclerViewAdapter.OnItemClickListener {
            override fun onItemClick(binding: ViewDataBinding, position: Int) {
                X5WebActivity.startActivity(mData[position].mBean.get()?.link)
            }
        })
    }

    private fun articleList() {

    }




}