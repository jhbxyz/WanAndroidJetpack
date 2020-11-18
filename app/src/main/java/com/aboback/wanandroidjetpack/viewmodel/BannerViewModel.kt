package com.aboback.wanandroidjetpack.viewmodel

import android.app.Application
import androidx.databinding.ObservableField
import com.aboback.base.ItemType
import com.aboback.base.rv.BaseMultiItemViewModel
import com.aboback.base.util.ActivityUtil
import com.aboback.base.util.getResDimen
import com.aboback.base.util.logWithTag
import com.aboback.base.viewmodel.BaseViewModel
import com.aboback.wanandroidjetpack.R
import com.aboback.wanandroidjetpack.base.ItemClick
import com.aboback.wanandroidjetpack.base.X5WebActivity
import com.aboback.wanandroidjetpack.base.X5WebViewModel
import com.aboback.wanandroidjetpack.common.CommonItemBean
import com.youth.banner.indicator.CircleIndicator
import com.youth.banner.indicator.Indicator
import com.youth.banner.listener.OnBannerListener
import com.youth.banner.listener.OnPageChangeListener

/**
 * @author jhb
 * @date 2020/10/28
 */
data class BannerBean(var imagePath: String?, var content: String?, var link: String?)

class BannerViewModel(app: Application) : BaseViewModel(app) {

    var mBannerHeight = ObservableField(R.dimen.dp_150.getResDimen().toInt())

    var mAdapterObservable = ObservableField<BannerAdapter>()
    var mIndicatorObservable = ObservableField<Indicator>(CircleIndicator(app))

    var mCurrentPage = 0
    var mPageChangeListener = object : SimpleOnPageChangeListener() {
        override fun onPageSelected(position: Int) {
            mCurrentPage = position
        }
    }

    var mBannerClickListener = OnBannerListener<BannerBean> { data, position ->
        "position = $position".logWithTag("1111111111111111")
        startActivity(X5WebActivity::class.java,
                X5WebViewModel.FLAG_BEAN to CommonItemBean(-1, data.content, data.link, false),
                X5WebViewModel.FLAG_SHOW_COLLECT_ICON to false
        )
    }

}

abstract class SimpleOnPageChangeListener : OnPageChangeListener {

    override fun onPageScrollStateChanged(state: Int) {}

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}

    override fun onPageSelected(position: Int) {}

}