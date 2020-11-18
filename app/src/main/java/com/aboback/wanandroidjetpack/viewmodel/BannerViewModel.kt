package com.aboback.wanandroidjetpack.viewmodel

import android.app.Application
import androidx.databinding.ObservableField
import com.aboback.base.ItemType
import com.aboback.base.rv.BaseMultiItemViewModel
import com.aboback.base.util.ActivityUtil
import com.aboback.base.util.getResDimen
import com.aboback.base.viewmodel.BaseViewModel
import com.aboback.wanandroidjetpack.R
import com.aboback.wanandroidjetpack.base.ItemClick
import com.aboback.wanandroidjetpack.base.X5WebActivity
import com.aboback.wanandroidjetpack.base.X5WebViewModel
import com.aboback.wanandroidjetpack.common.CommonItemBean
import com.youth.banner.indicator.CircleIndicator
import com.youth.banner.indicator.Indicator

/**
 * @author jhb
 * @date 2020/10/28
 */
class BannerViewModel(app: Application) : BaseViewModel(app), ItemClick {

    var mBannerHeight = ObservableField(R.dimen.dp_150.getResDimen().toInt())

    var mAdapterObservable = ObservableField<BannerAdapter>()
    var mIndicatorObservable = ObservableField<Indicator>(CircleIndicator(app))


    override fun onItemClick() {
        startActivity(X5WebActivity::class.java,
                X5WebViewModel.FLAG_BEAN to CommonItemBean(-1, "", "", false),
                X5WebViewModel.FLAG_SHOW_COLLECT_ICON to true
        )
    }

}