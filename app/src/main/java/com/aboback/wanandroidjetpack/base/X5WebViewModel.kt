package com.aboback.wanandroidjetpack.base

import android.app.Application
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.aboback.base.util.getResDrawable
import com.aboback.base.util.showToast
import com.aboback.base.util.truely
import com.aboback.base.viewmodel.BaseLayoutViewModel
import com.aboback.network.NetConstant
import com.aboback.wanandroidjetpack.R
import com.aboback.wanandroidjetpack.common.CommonItemBean
import com.aboback.wanandroidjetpack.network.WanServer
import com.aboback.wanandroidjetpack.util.launch
import com.aboback.wanandroidjetpack.util.response
import com.aboback.wanandroidjetpack.viewmodel.FabViewModel
import com.aboback.wanandroidjetpack.viewmodel.TitleViewModel
import kotlinx.coroutines.launch

/**
 * @author jhb
 * @date 2020/11/17
 */
class X5WebViewModel(app: Application) : BaseLayoutViewModel(app) {

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
                    unCollect()
                } else {
                    collect()
                }
            }
    )

    var showLoadingDialog = MutableLiveData<Boolean>()
    private fun collect() {
        viewModelScope.launch {
            try {
                showLoadingDialog.value = true
                val collect = WanServer.api.collect(mBean?.id)
                when (collect.errorCode) {
                    NetConstant.SUCCESS -> {
                        mBean?.collect = true
                        setCollectState(true)
                    }
                    else -> collect.errorMsg?.showToast()
                }
            } catch (e: Throwable) {
                e.printStackTrace()
                e.message?.showToast()
            } finally {
                showLoadingDialog.value = false
            }
        }
    }

    private fun unCollect() {
        viewModelScope.launch {
            try {
                showLoadingDialog.value = true
                val unCollect = WanServer.api.unCollect(mBean?.id)
                when (unCollect.errorCode) {
                    NetConstant.SUCCESS -> {
                        mBean?.collect = false
                        setCollectState(false)
                    }
                    else -> unCollect.errorMsg?.showToast()
                }
            } catch (e: Throwable) {
                e.printStackTrace()
                e.message?.showToast()
            } finally {
                showLoadingDialog.value = false
            }
        }
    }

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