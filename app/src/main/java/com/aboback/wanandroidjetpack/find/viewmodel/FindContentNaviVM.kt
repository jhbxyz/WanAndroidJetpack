package com.aboback.wanandroidjetpack.find.viewmodel

import android.app.Application
import com.aboback.base.rv.QuickAdapter
import com.aboback.base.util.falsely
import com.aboback.base.util.truely
import com.aboback.base.viewmodel.BaseRepositoryViewModel
import com.aboback.wanandroidjetpack.R
import com.aboback.wanandroidjetpack.bean.ItemDatasBean
import com.aboback.wanandroidjetpack.find.FindContentNaviRepository
import com.aboback.wanandroidjetpack.rv.RecyclerViewVM
import com.aboback.wanandroidjetpack.util.launch

/**
 * Created by jhb on 2020-11-06.
 * 体系和导航
 */
class FindContentNaviVM(app: Application) : BaseRepositoryViewModel<FindContentNaviRepository>(app, FindContentNaviRepository()) {

    private var mDataLeft = arrayListOf<ItemFindContentTreeAndNaviLeftVM>()
    private val mAdapterLeft = QuickAdapter(R.layout.item_rv_find_content_tree_and_navi_left, mDataLeft)

    private var mDataRight = arrayListOf<ItemFindContentTreeAndNaviRightVM>()
    private val mAdapterRight = QuickAdapter(R.layout.item_rv_find_content_tree_and_navi_right, mDataRight)

    private val mNaviMap = hashMapOf<Int?, List<ItemDatasBean>?>()

    var isRequestSuccess = false

    var rvVMLeft = RecyclerViewVM(app).apply {
        mAdapterObservable.set(mAdapterLeft)

    }

    var rvVMRight = RecyclerViewVM(app).apply {
        mAdapterObservable.set(mAdapterRight)
    }


    fun requestServer() {
        launch {
            naviList()
        }
    }

    private suspend fun naviList() {
        isRequestSuccess = true
        mRepo.naviList().data?.forEach {
            mNaviMap[it.cid] = it.articles
            mDataLeft.add(ItemFindContentTreeAndNaviLeftVM(getApplication()).apply {
                mContent.set(it.name)
                mCid = it.cid
                onClickItem = {
                    if (mChecked.get().falsely()) {
                        mDataLeft.find { it.mChecked.get().truely() }?.apply {
                            mChecked.set(false)
                        }
                        mChecked.set(true)

                        getNaviArticles(mCid)
                    }
                }
            })

        }
        val leftVM = mDataLeft[0]
        leftVM.mChecked.set(true)
        getNaviArticles(leftVM.mCid)
        mAdapterLeft.notifyDataSetChanged()
    }

    private fun getNaviArticles(cid: Int?) {
        mDataRight.clear()
        mNaviMap[cid]?.forEach {
            mDataRight.add(ItemFindContentTreeAndNaviRightVM(getApplication()).apply {
                mContent.set(it.title)
                onClickItem = {

                }
            })
        }
        mAdapterRight.notifyDataSetChanged()
    }


}