package com.aboback.wanandroidjetpack.home

import android.app.Application
import androidx.databinding.ObservableField
import androidx.lifecycle.viewModelScope
import com.aboback.base.viewmodel.BaseRepositoryViewModel
import com.aboback.wanandroidjetpack.R
import com.aboback.wanandroidjetpack.bean.ArticleListBean
import com.aboback.wanandroidjetpack.home.viewmodel.ItemHomeVM
import com.aboback.wanandroidjetpack.rv.BaseRecyclerViewAdapter
import com.aboback.wanandroidjetpack.rv.RecyclerViewVM
import com.aboback.wanandroidjetpack.viewmodel.TitleVM
import kotlinx.coroutines.launch

/**
 * Created by jhb on 2020-03-11.
 */
class HomeVM(app: Application) : BaseRepositoryViewModel<HomeRepository>(app, HomeRepository()) {


    var mTitleVM = TitleVM(
            app,
            leftText = "首页",
            leftDrawable = null,
            leftAction = {

            }
    )

    var mData = arrayListOf<ItemHomeVM>()
    val mAdapter = BaseRecyclerViewAdapter(R.layout.item_rv_home, mData)

    private var mCurrPage = 1

    var rvVM = RecyclerViewVM(app).apply {
        mRefreshEnable = false
        mAdapterObservable.set(mAdapter)

        mOnRefresh = {
            mIsRefreshing.set(true)

            mData.clear()
            mCurrPage = 1

            mIsRefreshing.set(false)
        }

        mOnLoadMoreListener = {
            mCurrPage++
        }
    }


    override fun onModelBind() {
        super.onModelBind()


        viewModelScope.launch {

            val articleList = mRepo.articleList(mCurrPage)
            articleList.data?.datas?.forEach {
                mData.add(ItemHomeVM(getApplication()).apply {
                    mTime.set(it.niceDate)
                    mTitle.set(it.title)
                    mAuthor.handleAuthor(it)
                    mCategory.set("分类: ${it.superChapterName}")
                })
            }

            mAdapter.notifyDataSetChanged()

        }
    }

    private fun ObservableField<String>.handleAuthor(bean: ArticleListBean.DataBean.DatasBean) {
        if (bean.author.isNullOrEmpty()) {
            set("分享人: ${bean.shareUser}")
        } else {
            set("作者: ${bean.author}")
        }

    }


}