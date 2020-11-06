package com.aboback.wanandroidjetpack.find.viewmodel

import android.app.Application
import com.aboback.base.rv.QuickAdapter
import com.aboback.base.util.falsely
import com.aboback.base.util.truely
import com.aboback.base.viewmodel.BaseRepositoryViewModel
import com.aboback.wanandroidjetpack.R
import com.aboback.wanandroidjetpack.bean.ItemDatasBean
import com.aboback.wanandroidjetpack.bean.TreeListBean
import com.aboback.wanandroidjetpack.find.FindContentTreeAndNaviRepository
import com.aboback.wanandroidjetpack.find.ui.FindContentTreeAndNaviPage
import com.aboback.wanandroidjetpack.rv.RecyclerViewVM
import com.aboback.wanandroidjetpack.util.launch

/**
 * Created by jhb on 2020-11-06.
 * 体系和导航
 */
class FindContentTreeAndNaviVM(private val mContentPage: FindContentTreeAndNaviPage, app: Application) : BaseRepositoryViewModel<FindContentTreeAndNaviRepository>(app, FindContentTreeAndNaviRepository(mContentPage)) {

    private var mDataLeft = arrayListOf<ItemFindContentTreeAndNaviLeftVM>()
    private val mAdapterLeft = QuickAdapter(R.layout.item_rv_find_content_tree_and_navi_left, mDataLeft)

    private var mDataRight = arrayListOf<ItemFindContentTreeAndNaviRightVM>()
    private val mAdapterRight = QuickAdapter(R.layout.item_rv_find_content_tree_and_navi_right, mDataRight)

    private val mNaviMap = hashMapOf<Int?, List<ItemDatasBean>?>()
    private val mTreeMap = hashMapOf<Int?, List<TreeListBean.DataBean.ChildrenBean?>?>()

    var rvVMLeft = RecyclerViewVM(app).apply {
        mAdapterObservable.set(mAdapterLeft)

    }

    var rvVMRight = RecyclerViewVM(app).apply {
        mAdapterObservable.set(mAdapterRight)
    }

    override fun onModelBind() {
        super.onModelBind()

        requestServer()
    }

    private fun requestServer() {
        launch {
            when (mContentPage) {
                FindContentTreeAndNaviPage.TREE -> {
                    treeList()
                }
                FindContentTreeAndNaviPage.NAVIGATION -> {
                    naviList()
                }
            }

        }
    }

    private suspend fun treeList() {
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
                onClickItem = {

                }
            })
        }
        mAdapterRight.notifyDataSetChanged()
    }

    private suspend fun naviList() {
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