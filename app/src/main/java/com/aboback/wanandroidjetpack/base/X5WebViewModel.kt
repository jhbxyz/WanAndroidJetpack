package com.aboback.wanandroidjetpack.base

import android.app.Application
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.aboback.base.util.getResDrawable
import com.aboback.base.util.showToast
import com.aboback.base.util.truely
import com.aboback.base.viewmodel.BaseLayoutViewModel
import com.aboback.base.viewmodel.BaseRepositoryViewModel
import com.aboback.network.NetConstant
import com.aboback.wanandroidjetpack.R
import com.aboback.wanandroidjetpack.bridge.GlobalSingle
import com.aboback.wanandroidjetpack.common.CommonItemBean
import com.aboback.wanandroidjetpack.network.WanServer
import com.aboback.wanandroidjetpack.util.*
import com.aboback.wanandroidjetpack.viewmodel.FabViewModel
import com.aboback.wanandroidjetpack.viewmodel.TitleViewModel
import kotlinx.coroutines.launch

/**
 * @author jhb
 * @date 2020/11/17
 */
class X5WebViewModel(app: Application) : BaseRepositoryViewModel<NetRepository>(app, NetRepository()) {

    companion object {
        const val FLAG_BEAN = "flag_bean"
    }

    val mScrollToTop = MutableLiveData<Boolean>()
    val mUrl = ObservableField("")
    private var mBean: CommonItemBean? = null

    val mTitleVM = TitleViewModel(
            leftAction = {
                finish()
            },
            title = "",
            rightAction = {
                if (mBean?.collect.truely()) {
                    mBean?.id?.let {
                        unCollectDelegate(it, mRepo, success = {
                            mBean?.collect = false
                            setCollectState(false)
                        })
                    }
                } else {
                    mBean?.id?.let {
                        collectDelegate(it, mRepo) {
                            mBean?.collect = true
                            setCollectState(true)
                        }
                    }
                }
            }
    )


    val mFabVM = FabViewModel(
            onClick = {
                mScrollToTop.value = true
            }
    )

    override fun onModelBind() {
        super.onModelBind()
        mBean = mBundle.getSerializable(FLAG_BEAN) as? CommonItemBean
        mBean?.apply {
            mUrl.set(link)
            mTitleVM.mTitle.set(title)
            setCollectState(collect)
        }

    }

    private fun setCollectState(collect: Boolean) {
        mTitleVM.mRightDrawable.set(if (collect) R.drawable.collect_red.getResDrawable() else R.drawable.sc_white_stroke_ico.getResDrawable())
    }

}