package com.jhb.wanandroidjetpack.category.interview_related.viewmodel

import android.app.Application
import androidx.databinding.ViewDataBinding
import com.jhb.wanandroidjetpack.R
import com.jhb.wanandroidjetpack.base.BaseLayoutViewModel
import com.jhb.wanandroidjetpack.base.X5WebActivity
import com.jhb.wanandroidjetpack.bean.ArticleListBean
import com.jhb.wanandroidjetpack.bean.TreeJsonBean
import com.jhb.wanandroidjetpack.bean.WendaListBean
import com.jhb.wanandroidjetpack.db.WenDaListManger
import com.jhb.wanandroidjetpack.net.WanService
import com.jhb.wanandroidjetpack.net.WanSubscriber
import com.jhb.wanandroidjetpack.rv.BaseRecyclerViewAdapter
import com.jhb.wanandroidjetpack.rv.RecyclerViewVM
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
        WanService.api.articleList(mCurrPage)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : WanSubscriber<ArticleListBean>() {
                override fun onSuccess(t: ArticleListBean) {
                    t.data?.datas?.forEach {
                        mData.add(ItemInterviewRelatedVM(getApplication()).apply {
                            mBean.set(it)
                        })
                    }
                    mAdapter.notifyDataSetChanged()

                }
            })
            .addToDisposable()
    }




}