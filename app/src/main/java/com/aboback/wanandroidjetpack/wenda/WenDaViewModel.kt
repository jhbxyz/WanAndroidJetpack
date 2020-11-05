package com.aboback.wanandroidjetpack.wenda

import android.app.Application
import androidx.databinding.ObservableField
import com.aboback.base.rv.QuickAdapter
import com.aboback.base.util.showToast
import com.aboback.base.viewmodel.BaseRepositoryViewModel
import com.aboback.wanandroidjetpack.R
import com.aboback.wanandroidjetpack.bean.ItemDatasBean
import com.aboback.wanandroidjetpack.home.viewmodel.ItemHomeVM
import com.aboback.wanandroidjetpack.rv.RecyclerViewVM
import com.aboback.wanandroidjetpack.util.launch
import com.aboback.wanandroidjetpack.util.loadSuccess
import com.aboback.wanandroidjetpack.util.response
import com.aboback.wanandroidjetpack.viewmodel.TagViewModel
import com.aboback.wanandroidjetpack.viewmodel.TitleViewModel

/**
 * Created by jhb on 2020-03-11.
 */
class WenDaViewModel(app: Application) : BaseRepositoryViewModel<WenDaRepository>(app, WenDaRepository()) {


    var mTitleVM = TitleViewModel(
            leftDrawable = null,
            title = "问答"

    )

    var mData = arrayListOf<ItemHomeVM>()
    val mAdapter = QuickAdapter(R.layout.item_rv_home, mData)

    private var mCurrPage = 0

    var rvVM = RecyclerViewVM(app).apply {
        mRefreshEnable = true
        mAdapterObservable.set(mAdapter)

        mOnRefresh = {
            mIsRefreshing.set(true)

            mCurrPage = 0

            requestServer(false)
        }

        mOnLoadMoreListener = {
            mCurrPage++
            requestServer(true)
        }
    }


    override fun onModelBind() {
        super.onModelBind()

        requestServer(true)
    }

    private fun resetDataIfNeed(showDialog: Boolean) {
        if (!showDialog) {
            mData.clear()
        }
    }

    private fun requestServer(showDialog: Boolean = true) {
        launch(showDialog) {
            response(mRepo.wendaList(mCurrPage)) {

                resetDataIfNeed(showDialog)

                data?.datas?.forEach {
                    bindData(it)
                }

                mAdapter.notifyDataSetChanged()

                if (!showDialog) {
                    rvVM.mIsRefreshing.set(false)
                } else {
                    loadSuccess()
                }
            }
        }
    }


    private fun bindData(bean: ItemDatasBean) {
        mData.add(ItemHomeVM(getApplication(), bean).apply {
            bindData()

            bean.tags?.forEach { tags ->
                mTagVMList.add(TagViewModel().apply {
                    mContent.set(tags.name)
                })
            }
        })
    }


}