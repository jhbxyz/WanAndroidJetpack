package com.aboback.wanandroidjetpack.main.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

/**
 * Created by jhb on 2020-02-11.
 */
class MainVPAdapter(var mDatas: List<Fragment>,
                    fragmentManager: FragmentManager,
                    lifecycle: Lifecycle) : FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int = mDatas.size

    override fun createFragment(position: Int): Fragment = mDatas[position]

}