package com.aboback.wanandroidjetpack.viewmodel

import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.youth.banner.adapter.BannerImageAdapter
import com.youth.banner.holder.BannerImageHolder

/**
 * @author jhb
 * @date 2020/10/28
 */
class BannerAdapter(mData: MutableList<String>?) : BannerImageAdapter<String>(mData) {
    override fun onBindView(holder: BannerImageHolder, data: String?, position: Int, size: Int) {
        //图片加载自己实现
        Glide.with(holder.itemView)
            .load(data)
            .apply(RequestOptions.bitmapTransform(RoundedCorners(30)))
            .into(holder.imageView);

    }
}