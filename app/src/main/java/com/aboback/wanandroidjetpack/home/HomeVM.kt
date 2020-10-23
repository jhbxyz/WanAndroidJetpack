package com.aboback.wanandroidjetpack.home

import android.app.Application
import androidx.lifecycle.viewModelScope
import com.aboback.base.BaseRepository
import com.aboback.base.viewmodel.BaseRepositoryViewModel
import com.aboback.wanandroidjetpack.R
import com.aboback.wanandroidjetpack.category.question.viewmodel.ItemDailyQuestionVM
import com.aboback.wanandroidjetpack.home.viewmodel.ItemHomeVM
import com.aboback.wanandroidjetpack.rv.BaseRecyclerViewAdapter
import com.aboback.wanandroidjetpack.rv.RecyclerViewVM
import com.aboback.wanandroidjetpack.util.logE
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
    private val oneDay = 1000 * 60 * 60 * 24

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

        for (i in 0..10) {
            mData.add(ItemHomeVM(getApplication()).apply {
                mContent.set("haha = $i")
            })
        }


//        viewModelScope.launch {
//            val articleListKt = mRepo.articleListKt(mCurrPage)
//            "${mTag}    articleListKt  = $articleListKt".logE()
//
//
//        }
    }


}