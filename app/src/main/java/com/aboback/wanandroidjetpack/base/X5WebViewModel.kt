package com.aboback.wanandroidjetpack.base

import android.app.Application
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import com.aboback.base.util.getResDrawable
import com.aboback.base.viewmodel.BaseLayoutViewModel
import com.aboback.wanandroidjetpack.R
import com.aboback.wanandroidjetpack.common.CommonItemBean
import com.aboback.wanandroidjetpack.viewmodel.FabViewModel
import com.aboback.wanandroidjetpack.viewmodel.TitleViewModel

/**
 * @author jhb
 * @date 2020/11/17
 */
class X5WebViewModel(app: Application) : BaseLayoutViewModel(app) {

    companion object {
        const val FLAG_BEAN = "flag_bean"
    }

    val mTitleVM = TitleViewModel(
            leftAction = {
                finish()
            },
            title = "",
            rightAction = {

            }
    )

    val mFabVM = FabViewModel(
            onClick = {
                mScrollToTop.value = true
            }
    )

    val mScrollToTop = MutableLiveData<Boolean>()
    val mUrl = ObservableField("")

    override fun onModelBind() {
        super.onModelBind()
        val bean = mBundle.getSerializable(FLAG_BEAN) as? CommonItemBean
        bean?.apply {
            mUrl.set(link)
            mTitleVM.mTitle.set(title)
            mTitleVM.mRightDrawable.set(if (collect) R.drawable.collect_red.getResDrawable() else R.drawable.sc_white_stroke_ico.getResDrawable())
        }
    }

}