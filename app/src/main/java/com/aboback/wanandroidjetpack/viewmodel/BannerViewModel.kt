package com.aboback.wanandroidjetpack.viewmodel

import android.app.Application
import androidx.databinding.ObservableField
import com.aboback.base.ItemType
import com.aboback.base.rv.BaseMultiItemViewModel
import com.aboback.base.util.ActivityUtil
import com.aboback.base.util.getResDimen
import com.aboback.wanandroidjetpack.R
import com.youth.banner.indicator.CircleIndicator
import com.youth.banner.indicator.Indicator

/**
 * @author jhb
 * @date 2020/10/28
 */
class BannerViewModel(app: Application) {

    var mBannerHeight = ObservableField(R.dimen.dp_150.getResDimen().toInt())

    var mAdapterObservable = ObservableField<BannerAdapter>()
    var mIndicatorObservable = ObservableField<Indicator>(CircleIndicator(app))

}