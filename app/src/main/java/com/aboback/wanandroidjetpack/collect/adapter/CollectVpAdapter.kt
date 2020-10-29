package com.aboback.wanandroidjetpack.collect.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

/**
 * @author jhb
 * @date 2020/10/29
 */
class CollectVpAdapter(private val mData: List<Fragment>, fragmentManager: FragmentManager, lifecycle: Lifecycle) : FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int = mData.size

    override fun createFragment(position: Int): Fragment = mData[position]


}