package com.aboback.wanandroidjetpack.home.viewmodel

import android.app.Application
import androidx.databinding.ObservableField
import androidx.lifecycle.viewModelScope
import com.aboback.base.ItemType
import com.aboback.base.rv.BaseMultiItemViewModel
import com.aboback.base.rv.QuickMultiAdapter
import com.aboback.base.viewmodel.BaseRepositoryViewModel
import com.aboback.wanandroidjetpack.R
import com.aboback.wanandroidjetpack.bean.ArticleDatasBean
import com.aboback.wanandroidjetpack.home.HomeRepository
import com.aboback.wanandroidjetpack.rv.RecyclerViewVM
import com.aboback.wanandroidjetpack.viewmodel.BannerAdapter
import com.aboback.wanandroidjetpack.viewmodel.BannerViewModel
import com.aboback.wanandroidjetpack.viewmodel.TagViewModel
import com.aboback.wanandroidjetpack.viewmodel.TitleViewModel
import kotlinx.coroutines.launch

/**
 * Created by jhb on 2020-03-11.
 */

enum class CurrPageState {
    INIT, REFRESH, LOAD_MORE
}

class HomeVM(app: Application) : BaseRepositoryViewModel<HomeRepository>(app, HomeRepository()) {

    var mTitleVM = TitleViewModel(
            leftDrawable = null,
            title = "首页"
    )

    private val mImageList = arrayListOf<String>()

    private val mBannerAdapter = BannerAdapter(mImageList)
    private var mBannerViewModel = BannerViewModel(getApplication()).apply {
        mAdapterObservable.set(mBannerAdapter)
    }

    private val mHomeBannerVM = HomeBannerVM(getApplication()).apply {
        mBannerVM.set(mBannerViewModel)
    }


    var mData = arrayListOf<BaseMultiItemViewModel>()
    val mAdapter = QuickMultiAdapter(mData).apply {
        addType(R.layout.item_rv_home_banner, ItemType.ITEM_HOME_BANNER)
        addType(R.layout.item_rv_home, ItemType.ITEM_HOME_MAIN)
    }

    private var mCurrPage = 0

    var rvVM = RecyclerViewVM(app).apply {
        mRefreshEnable = true
        mAdapterObservable.set(mAdapter)

        mOnRefresh = {
            mIsRefreshing.set(true)

            mData.clear()
            mCurrPage = 0
            requestServer(CurrPageState.REFRESH)

            mIsRefreshing.set(false)
        }

        mOnLoadMoreListener = {
            mCurrPage++
            requestServer(CurrPageState.LOAD_MORE)
        }
    }


    override fun onModelBind() {
        super.onModelBind()

        requestServer(CurrPageState.INIT)

    }


    private fun dialogState(state: CurrPageState, isShow: Boolean) {
        if (state != CurrPageState.REFRESH) {
            isDialogShow.value = isShow
        }
    }

    private fun requestServer(state: CurrPageState) {
        viewModelScope.launch {

            resetData(state)

            dialogState(state, true)

            getBannerImages(state)

            getArticleTop(state)

            getArticleList()

            mAdapter.notifyDataSetChanged()

            dialogState(state, false)
        }
    }

    private fun resetData(state: CurrPageState) {
        if (state == CurrPageState.REFRESH) {
            mData.clear()
        }
    }

    private suspend fun getBannerImages(state: CurrPageState) {
        if (state == CurrPageState.INIT || state == CurrPageState.REFRESH) {
            mImageList.clear()
            mRepo.banner().data?.forEach {
                mImageList.add(it?.imagePath ?: "")
            }
            mData.add(mHomeBannerVM)
        }
    }

    private suspend fun getArticleTop(state: CurrPageState) {
        if (state == CurrPageState.REFRESH || state == CurrPageState.INIT) {
            val articleTop = mRepo.articleTop()
            articleTop.data?.forEach {
                bindData(it)
            }
        }
    }

    private suspend fun getArticleList() {
        val articleList = mRepo.articleList(mCurrPage)
        articleList.data?.datas?.forEach {
            bindData(it)
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