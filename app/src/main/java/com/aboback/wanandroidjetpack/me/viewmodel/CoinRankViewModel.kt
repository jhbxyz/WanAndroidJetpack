package com.aboback.wanandroidjetpack.me.viewmodel

import android.app.Application
import androidx.databinding.ObservableField
import com.aboback.base.rv.QuickAdapter
import com.aboback.base.viewmodel.BaseRepositoryViewModel
import com.aboback.wanandroidjetpack.R
import com.aboback.wanandroidjetpack.bean.CoinUserInfoBean
import com.aboback.wanandroidjetpack.me.CoinRankRepository
import com.aboback.wanandroidjetpack.rv.RecyclerViewVM
import com.aboback.wanandroidjetpack.util.launch
import com.aboback.wanandroidjetpack.util.loadSuccess
import com.aboback.wanandroidjetpack.util.noMoreData
import com.aboback.wanandroidjetpack.util.response
import com.aboback.wanandroidjetpack.viewmodel.TitleViewModel

/**
 * @author jhb
 * @date 2020/11/11
 */
class CoinRankViewModel(app: Application) : BaseRepositoryViewModel<CoinRankRepository>(app, CoinRankRepository()) {

    companion object {
        const val COIN_USER_INFO_BEAN = "coin_user_info_bean"
    }

    var mTitleVM = TitleViewModel(
            leftAction = {
                finish()
            },
            title = "积分排行榜"
    )

    var mRank = ObservableField("")
    var mName = ObservableField("")
    var mCount = ObservableField("")

    private var mData = arrayListOf<ItemCoinRankVM>()
    private val mAdapter = QuickAdapter(R.layout.item_rv_coin_rank, mData)

    private var mCurrPage = 1
    private var mPageCount = 1


    var rvVM = RecyclerViewVM(app).apply {
        mRefreshEnable = true
        mAdapterObservable.set(mAdapter)

        mOnRefresh = {
            mIsRefreshing.set(true)

            mCurrPage = 1

            requestServer(false)
        }

        mOnLoadMoreListener = {
            mCurrPage++
            if (mCurrPage < mPageCount) {
                requestServer(true)
            } else {
                noMoreData()
            }
        }
    }

    override fun onModelBind() {
        super.onModelBind()
        (mBundle.getSerializable(COIN_USER_INFO_BEAN) as? CoinUserInfoBean)?.data?.apply {
            mRank.set(rank)
            mName.set(username)
            mCount.set(coinCount?.toString())
        }

        requestServer(true)
    }

    private fun requestServer(showDialog: Boolean) {
        launch(showDialog) {
            response(mRepo.coinRankList(mCurrPage)) {
                if (!showDialog) {
                    mData.clear()
                    rvVM.mIsRefreshing.set(false)
                }
                mPageCount = data?.pageCount ?: 1
                data?.datas?.forEach {
                    mData.add(ItemCoinRankVM(getApplication()).apply {
                        mRank.set(it.rank)
                        mName.set(it.username)
                        mCount.set(it.coinCount?.toString())
                    })
                }

                mAdapter.notifyDataSetChanged()

                if (showDialog) {
                    loadSuccess()
                }

            }
        }
    }

}