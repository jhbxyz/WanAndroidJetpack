package com.aboback.wanandroidjetpack.home.viewmodel

import android.app.Application
import androidx.databinding.ObservableField
import androidx.lifecycle.viewModelScope
import com.aboback.base.ItemType
import com.aboback.base.rv.BaseMultiItemViewModel
import com.aboback.base.rv.QuickMultiAdapter
import com.aboback.base.util.showToast
import com.aboback.base.viewmodel.BaseRepositoryViewModel
import com.aboback.wanandroidjetpack.R
import com.aboback.wanandroidjetpack.bean.ItemDatasBean
import com.aboback.wanandroidjetpack.home.HomeRepository
import com.aboback.wanandroidjetpack.rv.RecyclerViewVM
import com.aboback.wanandroidjetpack.viewmodel.*
import kotlinx.coroutines.launch

/**
 * Created by jhb on 2020-03-11.
 */

enum class HomePageState {
    INIT, REFRESH, LOAD_MORE
}

class HomeVM(app: Application) : BaseRepositoryViewModel<HomeRepository>(app, HomeRepository()) {

    var mTitleVM = TitleViewModel(
            leftDrawable = null,
            title = "首页"
    )

//    var mFabVM = FabViewModel(
//            onClick = {
//                rvVM.mScrollToTop.set(true)
//            }
//    )
//    var mFabVisible = ObservableField(false)

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
            requestServer(HomePageState.REFRESH)

        }

        mOnLoadMoreListener = {
            mCurrPage++
            requestServer(HomePageState.LOAD_MORE)
        }
//        mOnScrollListener.set(object : RvScrollListener() {
//            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
//                super.onScrollStateChanged(recyclerView, newState)
//                "onScrolled  newState = $newState".log()
//                // -1 表示向上滚动, 返回值 true 表示 还可以往上滑动
//                if (!recyclerView.canScrollVertically(-1)) {
//                    mScrollToTop.set(false)
//                    mFabVisible.set(false)
//                }
//            }
//
//            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
//                super.onScrolled(recyclerView, dx, dy)
//                if (dy < 0 && mFabVisible.get().falsely()) {
//                    mFabVisible.set(true)
//                } else if (dy > 0 && mFabVisible.get().truely()) {
//                    mFabVisible.set(false)
//                }
//            }
//        })
    }


    override fun onModelBind() {
        super.onModelBind()

        requestServer(HomePageState.INIT)

    }


    private fun dialogState(state: HomePageState, isShow: Boolean) {
        if (state != HomePageState.REFRESH) {
            isDialogShow.value = isShow
            if (!isShow) {
                "加载成功".showToast()
            }
        }
    }

    private fun hideRefreshLoading(state: HomePageState) {
        if (state == HomePageState.REFRESH) {
            rvVM.mIsRefreshing.set(false)
        }
    }

    private fun requestServer(state: HomePageState) {
        viewModelScope.launch {
            try {
                resetDataIfNeed(state)

                dialogState(state, true)

                getBannerImages(state)

                getArticleTop(state)

                getArticleList()

            } catch (e: Throwable) {
                e.message?.showToast()

            } finally {
                mAdapter.notifyDataSetChanged()

                hideRefreshLoading(state)

                dialogState(state, false)
            }
        }
    }

    private fun resetDataIfNeed(state: HomePageState) {
        if (state == HomePageState.REFRESH) {
            mData.clear()
        }
    }

    private suspend fun getBannerImages(state: HomePageState) {
        if (state == HomePageState.INIT || state == HomePageState.REFRESH) {
            mImageList.clear()
            mRepo.banner().data?.forEach {
                mImageList.add(it?.imagePath ?: "")
            }
            mData.add(mHomeBannerVM)
        }
    }

    private suspend fun getArticleTop(state: HomePageState) {
        if (state == HomePageState.REFRESH || state == HomePageState.INIT) {
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

    private fun bindData(it: ItemDatasBean) {
        mData.add(ItemHomeVM(getApplication()).apply {
            mTime.set(it.niceDate)
            mTitle.set(it.title)
            mAuthor.handleAuthor(it)
            mCategory.set("分类: ${it.superChapterName}")

//            mTagList.set(it.tags)
//            bindTagVM()

            mTagVMList.clear()
            it.tags?.forEach { tags ->
                mTagVMList.add(TagViewModel().apply {
                    mContent.set(tags.name)
                })
            }
        })
    }

    private fun ObservableField<String>.handleAuthor(bean: ItemDatasBean) {
        if (bean.author.isNullOrEmpty()) {
            set("分享人: ${bean.shareUser}")
        } else {
            set("作者: ${bean.author}")
        }

    }


}