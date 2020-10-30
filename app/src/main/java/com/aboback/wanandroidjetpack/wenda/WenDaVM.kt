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
import com.aboback.wanandroidjetpack.util.response
import com.aboback.wanandroidjetpack.viewmodel.TagViewModel
import com.aboback.wanandroidjetpack.viewmodel.TitleViewModel

/**
 * Created by jhb on 2020-03-11.
 */
class WenDaVM(app: Application) : BaseRepositoryViewModel<WenDaRepository>(app, WenDaRepository()) {


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
                    "加载成功".showToast()
                }
            }
        }
    }


    private fun bindData(it: ItemDatasBean) {
        mData.add(ItemHomeVM(getApplication()).apply {
            mTime.set(it.niceDate)
            mTitle.set(it.title)
            mAuthor.handleAuthor(it)
            mCategory.set("分类: ${it.superChapterName}")

            it.tags?.forEach { tags ->
                mTagVMList.add(TagViewModel().apply {
                    mContent.set(tags.name)
                })
            }
        })
    }

    private fun ObservableField<String>.handleAuthor(bean: ItemDatasBean) {
        if (bean.author.isNullOrEmpty()) {
            set("分享人: ${bean.shareUser}")
        } else {
            set("作者: ${bean.author}")
        }

    }


}