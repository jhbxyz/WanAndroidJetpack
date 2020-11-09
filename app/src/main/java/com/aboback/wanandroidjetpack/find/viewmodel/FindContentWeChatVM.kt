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

/**
 * Created by jhb on 2020-03-11.
 */
class FindContentWeChatVM(app: Application) : BaseRepositoryViewModel<FindContentWeChatRepository>(app, FindContentWeChatRepository()) {

    var mDataLeft = arrayListOf<ItemFindContentWeChatLeftVM>()
    val mAdapterLeft = QuickAdapter(R.layout.item_rv_find_content_we_chat_left, mDataLeft)

    var mDataRight = arrayListOf<ItemHomeVM>()
    val mAdapterRight = QuickAdapter(R.layout.item_rv_home, mDataRight)

    private val mNaviMap = hashMapOf<Int?, List<ItemDatasBean>?>()

    private var mCurrPage = 0
    private var mPageCount = 1

    var rvVMLeft = RecyclerViewVM(app).apply {
        mAdapterObservable.set(mAdapterLeft)

    }

    var rvVMRight = RecyclerViewVM(app).apply {
        mAdapterObservable.set(mAdapterRight)

//        mOnRefresh = {
//            mIsRefreshing.set(true)
//
//            mCurrPage = 0
//
//        }
//
//        mOnLoadMoreListener = {
//            mCurrPage++
//            if (mCurrPage < mPageCount) {
//            } else {
//                noMoreData()
//            }
//        }
    }

    override fun onModelBind() {
        super.onModelBind()

        requestServer()
    }

    fun requestServer() {
        launch {
            val data = mRepo.weChatList().data
            data?.forEach {
                mDataLeft.add(ItemFindContentWeChatLeftVM(getApplication()).apply {
                    mContent.set(it?.name)
                    onClickItem = {
                        if (mChecked.get().falsely()) {
                            mDataLeft.find { it.mChecked.get().truely() }?.apply {
                                mChecked.set(false)
                            }
                            mChecked.set(true)
                            weChatListDetail(it?.id)
                        }
                    }
                })
            }

            mDataLeft[0].mChecked.set(true)
            weChatListDetail(data?.get(0)?.id)

            mAdapterLeft.notifyDataSetChanged()
        }
    }

    private fun weChatListDetail(id: Int?) {
        launch {
            mDataRight.clear()
            mRepo.weChatListDetail(id, mCurrPage).data?.datas?.forEach {
                mDataRight.add(ItemHomeVM(getApplication(), it).apply {
                    bindData()
                })
            }
            mAdapterRight.notifyDataSetChanged()
        }
    }


}