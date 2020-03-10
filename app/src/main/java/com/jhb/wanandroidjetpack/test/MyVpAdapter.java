package com.jhb.wanandroidjetpack.test;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.jhb.wanandroidjetpack.R;

import java.util.List;

/**
 * Created by jhb on 2020-03-03.
 */
public class MyVpAdapter extends PagerAdapter {

    private List<String> urls;
    private List<String> title;

    public MyVpAdapter(List<String> urls, List<String> title) {
        this.urls = urls;
        this.title = title;
    }



    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.item_vp_test, container, false);
        ImageView imageView = view.findViewById(R.id.ivTest);
        Glide.with(imageView).load(urls.get(position)).into(imageView);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return urls.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return title.get(position);
    }
}
