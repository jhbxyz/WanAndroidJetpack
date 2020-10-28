package com.aboback.wanandroidjetpack.test

import android.app.Application
import com.aboback.base.viewmodel.BaseLayoutViewModel
import com.aboback.wanandroidjetpack.viewmodel.BannerAdapter
import com.aboback.wanandroidjetpack.viewmodel.BannerViewModel
import com.youth.banner.indicator.CircleIndicator
import java.util.ArrayList

/**
 * @author jhb
 * @date 2020/10/28
 */
class BannerTestVM(app: Application) : BaseLayoutViewModel(app) {
    val imgList = ArrayList<String>()

    var bannerVM = BannerViewModel(getApplication()).apply {
        mAdapterObservable.set(BannerAdapter(imgList))
        mIndicatorObservable.set(CircleIndicator(getApplication()))
    }

    override fun onModelBind() {
        super.onModelBind()


        imgList.add("https://www.wanandroid.com/blogimgs/62c1bd68-b5f3-4a3c-a649-7ca8c7dfabe6.png")
        imgList.add("https://www.wanandroid.com/blogimgs/50c115c2-cf6c-4802-aa7b-a4334de444cd.png")
        imgList.add("https://wanandroid.com/blogimgs/affe33fb-a160-4c63-bcc5-2ba83965a7bc.png")

    }
}