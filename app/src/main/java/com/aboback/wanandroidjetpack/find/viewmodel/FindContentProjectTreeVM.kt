package com.aboback.wanandroidjetpack.find.viewmodel

import android.app.Application
import android.os.SystemClock
import androidx.lifecycle.viewModelScope
import com.aboback.base.rv.QuickAdapter
import com.aboback.base.util.falsely
import com.aboback.base.util.log
import com.aboback.base.util.logWithTag
import com.aboback.base.util.truely
import com.aboback.base.viewmodel.BaseRepositoryViewModel
import com.aboback.wanandroidjetpack.R
import com.aboback.wanandroidjetpack.bean.ItemDatasBean
import com.aboback.wanandroidjetpack.find.FindContentProjectTreeRepository
import com.aboback.wanandroidjetpack.rv.RecyclerViewVM
import com.aboback.wanandroidjetpack.util.launch
import com.aboback.wanandroidjetpack.util.loadSuccess
import com.aboback.wanandroidjetpack.util.netError
import com.aboback.wanandroidjetpack.util.noMoreData
import kotlinx.coroutines.launch

/**
 * Created by jhb on 2020-03-11.
 */
class FindContentProjectTreeVM(app: Application) : BaseRepositoryViewModel<FindContentProjectTreeRepository>(app, FindContentProjectTreeRepository()) {

    private var mDataLeft = arrayListOf<ItemFindContentProjectTreeLeftVM>()
    private val mAdapterLeft = QuickAdapter(R.layout.item_rv_find_content_project_tree_left, mDataLeft)

    private var mDataRight = arrayListOf<ItemFindContentProjectTreeRightVM>()
    private val mAdapterRight = QuickAdapter(R.layout.item_rv_find_content_project_tree_right, mDataRight)

    private var mCurrPage = 0
    private var mPageCount = 1

    var isRequestSuccess = false

    var rvVMLeft = RecyclerViewVM(app).apply {
        mAdapterObservable.set(mAdapterLeft)

    }

    var rvVMRight = RecyclerViewVM(app).apply {
        mRefreshEnable = true
        mAdapterObservable.set(mAdapterRight)

        mOnRefresh = {
            mIsRefreshing.set(true)

            mCurrPage = 0
            projectList(mId, isRefresh = true)

        }

        mOnLoadMoreListener = {
            mCurrPage++
            if (mCurrPage < mPageCount) {
                projectList(mId)
            } else {
                noMoreData()
            }
        }
    }

    fun requestServer() {

        viewModelScope.launch {
            try {
                isDialogShow.value = true
                isRequestSuccess = true
                mRepo.projectTreeList().data?.forEach {
                    mDataLeft.add(ItemFindContentProjectTreeLeftVM(getApplication()).apply {
                        mContent.set(it.name)
                        mCid = it.id
                        onClickItem = {
                            if (mChecked.get().falsely()) {
                                mDataLeft.find { it.mChecked.get().truely() }?.mChecked?.set(false)
                                mChecked.set(true)
                                projectList(mCid, isClick = true)
                            }
                        }
                    })
                }

                val leftVM = mDataLeft[0]
                leftVM.mChecked.set(true)
                mAdapterLeft.notifyDataSetChanged()

                projectList(leftVM.mCid)

            } catch (e: Exception) {
                e.netError()
            } finally {
            }


        }
    }

    private var mId: Int? = null
    private fun projectList(id: Int?, isRefresh: Boolean = false, isClick: Boolean = false) {
        mId = id

        viewModelScope.launch {
            try {
                if (isRefresh) {
                    mDataRight.clear()
                } else {
                    if (isClick) {
                        mDataRight.clear()
                    }
                    isDialogShow.value = true
                }
                val data = mRepo.projectListCid(mCurrPage, id).data
                mPageCount = data?.pageCount ?: 1
                data?.datas?.forEach {
                    mDataRight.add(ItemFindContentProjectTreeRightVM(getApplication()).apply {
                        mPath.set(it.envelopePic)
                        mTitle.set(it.title)
                        mDesc.set(it.desc)
                        mTime.set(it.niceShareDate)
                        mAuthor.set(it.author)
                    })
                }

                if (!isRefresh) {
                    loadSuccess()
                }

                mAdapterRight.notifyDataSetChanged()

            } catch (e: Exception) {
                e.netError()
            } finally {
                if (isRefresh) {
                    rvVMRight.mIsRefreshing.set(false)
                } else {
                    isDialogShow.value = false
                }
            }
        }
    }


}