package com.aboback.wanandroidjetpack.home

import android.app.Application
import androidx.databinding.ObservableField
import androidx.lifecycle.viewModelScope
import com.aboback.base.rv.QuickAdapter
import com.aboback.base.viewmodel.BaseRepositoryViewModel
import com.aboback.wanandroidjetpack.R
import com.aboback.wanandroidjetpack.bean.ArticleDatasBean
import com.aboback.wanandroidjetpack.home.viewmodel.ItemHomeVM
import com.aboback.wanandroidjetpack.rv.BaseRecyclerViewAdapter
import com.aboback.wanandroidjetpack.rv.RecyclerViewVM
import com.aboback.wanandroidjetpack.util.launch
import com.aboback.wanandroidjetpack.viewmodel.TagViewModel
import com.aboback.wanandroidjetpack.viewmodel.TitleVM
import kotlinx.coroutines.launch

/**
 * Created by jhb on 2020-03-11.
 */
class HomeVM(app: Application) : BaseRepositoryViewModel<HomeRepository>(app, HomeRepository()) {


    var mTitleVM = TitleVM(
            leftDrawable = null,
            title = "首页"
    )

    var mData = arrayListOf<ItemHomeVM>()
    val mAdapter = QuickAdapter(R.layout.item_rv_home, mData)

    private var mCurrPage = 0

    var rvVM = RecyclerViewVM(app).apply {
        mRefreshEnable = true
        mAdapterObservable.set(mAdapter)

        mOnRefresh = {
            mIsRefreshing.set(true)

            mData.clear()
            mCurrPage = 0
            requestServer()
            mIsRefreshing.set(false)
        }

        mOnLoadMoreListener = {
            mCurrPage++
            loadMore()
        }
    }


    override fun onModelBind() {
        super.onModelBind()

        requestServer()

    }

    private fun requestServer() {
        viewModelScope.launch {

            val articleTop = mRepo.articleTop()
            val articleList = mRepo.articleList(mCurrPage)

            articleTop.data?.forEach {
                bindData(it)
            }

            articleList.data?.datas?.forEach {
                bindData(it)
            }

            mAdapter.notifyDataSetChanged()

        }
    }


    private fun loadMore() {
        viewModelScope.launch {
            val articleList = mRepo.articleList(mCurrPage)
            articleList.data?.datas?.forEach {
                bindData(it)
            }
            mAdapter.notifyDataSetChanged()
        }

    }

    private fun bindData(it: ArticleDatasBean) {
        mData.add(ItemHomeVM(getApplication()).apply {
            mTime.set(it.niceDate)
            mTitle.set(it.title)
            mAuthor.handleAuthor(it)
            mCategory.set("分类: ${it.superChapterName}")

            mTagList.clear()
            it.tags?.forEach { tags ->
                mTagList.add(TagViewModel().apply {
                    mContent.set(tags.name)
                })
            }
        })
    }

    private fun ObservableField<String>.handleAuthor(bean: ArticleDatasBean) {
        if (bean.author.isNullOrEmpty()) {
            set("分享人: ${bean.shareUser}")
        } else {
            set("作者: ${bean.author}")
        }

    }


}