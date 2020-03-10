package com.jhb.wanandroidjetpack.util;

import android.util.Log;
import android.view.View;

import androidx.viewpager.widget.ViewPager;

/**
 * Created by jhb on 2020-03-03.
 */
public class GalleryTransFormer implements ViewPager.PageTransformer {

    private static final float MAX_SCALE = 0.9f;

    @Override
    public void transformPage(View page, float position) {

        Log.e("jiang","     position = "+position);

        if (position < -1 || position > 1) {
            //不可见区域
//            page.setScaleX(MAX_SCALE);
            page.setScaleY(MAX_SCALE);
        } else {
            //可见区域，缩放效果
            float scale = Math.max(MAX_SCALE, 1 - Math.abs(position));
//            page.setScaleX(scale);
            page.setScaleY(scale);
        }
    }
}
