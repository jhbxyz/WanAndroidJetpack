package com.aboback.wanandroidjetpack.find.viewmodel

import android.app.Application
import com.aboback.base.rv.QuickAdapter
import com.aboback.base.viewmodel.BaseRepositoryViewModel
import com.aboback.wanandroidjetpack.R
import com.aboback.wanandroidjetpack.bean.ItemDatasBean
import com.aboback.wanandroidjetpack.find.FindContentProjectTreeRepository
import com.aboback.wanandroidjetpack.rv.RecyclerViewVM
import com.aboback.wanandroidjetpack.util.launch
import com.aboback.wanandroidjetpack.util.noMoreData

/**
 * Created by jhb on 2020-03-11.
 */
class FindContentProjectTreeVM(app: Application) : BaseRepositoryViewModel<FindContentProjectTreeRepository>(app, FindContentProjectTreeRepository()) {

    var mDataLeft = arrayListOf<ItemFindContentProjectTreeLeftVM>()
    val mAdapterLeft = QuickAdapter(R.layout.item_rv_find_content_project_tree_left, mDataLeft)

    var mDataRight = arrayListOf<ItemFindContentProjectTreeRightVM>()
    val mAdapterRight = QuickAdapter(R.layout.item_rv_find_content_project_tree_right, mDataRight)

    private val mNaviMap = hashMapOf<Int?, List<ItemDatasBean>?>()

    private var mCurrPage = 0
    private var mPageCount = 1

    var rvVMLeft = RecyclerViewVM(app).apply {
        mAdapterObservable.set(mAdapterLeft)

    }

    var rvVMRight = RecyclerViewVM(app).apply {
        mAdapterObservable.set(mAdapterRight)

        mOnRefresh = {
            mIsRefreshing.set(true)

            mCurrPage = 0

        }

        mOnLoadMoreListener = {
            mCurrPage++
            if (mCurrPage < mPageCount) {

            } else {
                noMoreData()
            }
        }
    }

    override fun onModelBind() {
        super.onModelBind()

        requestServer()
    }

    fun requestServer() {
        launch {
            mRepo.projectList(mCurrPage)


        }
    }

    private fun weChatListDetail(id: Int?) {
        launch {
            mDataRight.clear()
            mRepo.weChatListDetail(id, mCurrPage).data?.datas?.forEach {
                mDataRight.add(ItemFindContentProjectTreeRightVM(getApplication()).apply {
                    mContent.set(it.title)
                    onClickItem = {

                    }
                })
            }
            mAdapterRight.notifyDataSetChanged()
        }
    }


}