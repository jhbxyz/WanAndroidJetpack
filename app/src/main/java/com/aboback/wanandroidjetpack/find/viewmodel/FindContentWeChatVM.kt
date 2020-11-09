package com.aboback.wanandroidjetpack.find.viewmodel

import android.app.Application
import com.aboback.base.rv.QuickAdapter
import com.aboback.base.util.falsely
import com.aboback.base.util.truely
import com.aboback.base.viewmodel.BaseRepositoryViewModel
import com.aboback.wanandroidjetpack.R
import com.aboback.wanandroidjetpack.bean.ItemDatasBean
import com.aboback.wanandroidjetpack.find.FindContentWeChatRepository
import com.aboback.wanandroidjetpack.home.viewmodel.ItemHomeVM
import com.aboback.wanandroidjetpack.rv.RecyclerViewVM
import com.aboback.wanandroidjetpack.util.launch
import com.aboback.wanandroidjetpack.util.loadSuccess
import com.aboback.wanandroidjetpack.util.noMoreData

/**
 * Created by jhb on 2020-03-11.
 */
class FindContentWeChatVM(app: Application) : BaseRepositoryViewModel<FindContentWeChatRepository>(app, FindContentWeChatRepository()) {

    private var mDataLeft = arrayListOf<ItemFindContentWeChatLeftVM>()
    private val mAdapterLeft = QuickAdapter(R.layout.item_rv_find_content_we_chat_left, mDataLeft)
    private var mDataRight = arrayListOf<ItemHomeVM>()
    private val mAdapterRight = QuickAdapter(R.layout.item_rv_home, mDataRight)

    var isRequestSuccess = false
    private var mCurrPage = 0
    private var mPageCount = 1
    private var mCurrId: Int? = 0


    var rvVMLeft = RecyclerViewVM(app).apply {
        mAdapterObservable.set(mAdapterLeft)

    }

    var rvVMRight = RecyclerViewVM(app).apply {
        mRefreshEnable = true
        mAdapterObservable.set(mAdapterRight)

        mOnRefresh = {
            mIsRefreshing.set(true)
            mCurrPage = 0
            weChatListDetail(mCurrId, isRefresh = true)
        }

        mOnLoadMoreListener = {
            mCurrPage++
            if (mCurrPage < mPageCount) {
                weChatListDetail(mCurrId)
            } else {
                noMoreData()
            }
        }
    }

    fun requestServer() {
        launch {
            isRequestSuccess = true
            val data = mRepo.weChatList().data
            data?.forEach {
                mDataLeft.add(ItemFindContentWeChatLeftVM(getApplication()).apply {
                    mContent.set(it?.name)
                    mId = it?.id
                    onClickItem = {
                        if (mChecked.get().falsely()) {
                            mDataLeft.find { it.mChecked.get().truely() }?.apply {
                                mChecked.set(false)
                            }
                            mChecked.set(true)
                            mCurrPage = 0
                            weChatListDetail(it?.id, isClick = true)
                        }
                    }
                })
            }

            mDataLeft[0].mChecked.set(true)
            weChatListDetail(data?.get(0)?.id)

            mAdapterLeft.notifyDataSetChanged()
        }
    }

    private fun weChatListDetail(id: Int?, isClick: Boolean = false, isRefresh: Boolean = false) {
        mCurrId = id

        launch(showDialog = !isRefresh, finish = {
            if (isRefresh) {
                rvVMRight.mIsRefreshing.set(false)
            }
        }) {
            if (isRefresh || isClick) {
                mDataRight.clear()
            }

            val data = mRepo.weChatListDetail(id, mCurrPage).data
            mPageCount = data?.pageCount ?: 1
            data?.datas?.forEach {
                mDataRight.add(ItemHomeVM(getApplication(), it).apply {
                    bindData()
                })
            }
            mAdapterRight.notifyDataSetChanged()
            if (!isRefresh) {
                loadSuccess()
            }
        }
    }


}