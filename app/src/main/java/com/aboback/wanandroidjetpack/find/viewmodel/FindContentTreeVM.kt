package com.aboback.wanandroidjetpack.find.viewmodel

import android.app.Application
import com.aboback.base.rv.QuickAdapter
import com.aboback.base.util.falsely
import com.aboback.base.util.logWithTag
import com.aboback.base.util.truely
import com.aboback.base.viewmodel.BaseRepositoryViewModel
import com.aboback.wanandroidjetpack.R
import com.aboback.wanandroidjetpack.bean.TreeListBean
import com.aboback.wanandroidjetpack.find.FindContentTreeRepository
import com.aboback.wanandroidjetpack.rv.RecyclerViewVM
import com.aboback.wanandroidjetpack.util.launch

/**
 * Created by jhb on 2020-11-06.
 * 体系和导航
 */
class FindContentTreeVM(app: Application) : BaseRepositoryViewModel<FindContentTreeRepository>(app, FindContentTreeRepository()) {

    private var mDataLeft = arrayListOf<ItemFindContentTreeAndNaviLeftVM>()
    private val mAdapterLeft = QuickAdapter(R.layout.item_rv_find_content_tree_and_navi_left, mDataLeft)

    private var mDataRight = arrayListOf<ItemFindContentTreeAndNaviRightVM>()
    private val mAdapterRight = QuickAdapter(R.layout.item_rv_find_content_tree_and_navi_right, mDataRight)

    private val mTreeMap = hashMapOf<Int?, List<TreeListBean.DataBean.ChildrenBean?>?>()

    var isRequestSuccess = false

    var rvVMLeft = RecyclerViewVM(app).apply {
        mAdapterObservable.set(mAdapterLeft)

    }

    var rvVMRight = RecyclerViewVM(app).apply {
        mAdapterObservable.set(mAdapterRight)
    }

    fun requestServer() {
        launch {
            treeList()
        }
    }

    private suspend fun treeList() {
        isRequestSuccess = true
        mDataLeft.clear()
        mRepo.treeList().data?.forEach {

            mTreeMap[it?.id] = it?.children

            mDataLeft.add(ItemFindContentTreeAndNaviLeftVM(getApplication()).apply {
                mContent.set(it?.name)
                mCid = it?.id
                onClickItem = {
                    if (mChecked.get().falsely()) {
                        mDataLeft.find { it.mChecked.get().truely() }?.apply {
                            mChecked.set(false)
                        }
                        mChecked.set(true)

                        getTreeArticles(mCid)
                    }
                }
            })

        }
        val leftVM = mDataLeft[0]
        leftVM.mChecked.set(true)
        getTreeArticles(leftVM.mCid)

        mAdapterLeft.notifyDataSetChanged()
    }

    private fun getTreeArticles(cid: Int?) {
        mDataRight.clear()
        mTreeMap[cid]?.forEach {
            mDataRight.add(ItemFindContentTreeAndNaviRightVM(getApplication()).apply {
                mContent.set(it?.name)
                mCid = it?.id
            })
        }
        mAdapterRight.notifyDataSetChanged()
    }

}