package com.aboback.wanandroidjetpack.collect.ui

import android.app.Application
import androidx.fragment.app.Fragment
import com.aboback.base.ui.BaseVMRepositoryFragment
import com.aboback.wanandroidjetpack.R
import com.aboback.wanandroidjetpack.collect.adapter.CollectVpAdapter
import com.aboback.wanandroidjetpack.collect.viewmodel.CollectVM
import com.aboback.wanandroidjetpack.main.ui.FragmentTest
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_collect.*

/**
 * @author jhb
 * @date 2020/10/29
 */
class CollectFragment : BaseVMRepositoryFragment<CollectVM>(R.layout.fragment_collect) {
    private val mFragments = arrayListOf<Fragment>()
    private val mTitles = arrayOf("收藏文章", "分享文章", "收藏网站", "分享项目")

    override fun getViewModel(app: Application) = CollectVM(app)

    override fun onViewInit() {
        super.onViewInit()

        mFragments.add(FragmentTest(6))
        mFragments.add(FragmentTest(7))
        mFragments.add(FragmentTest(8))
        mFragments.add(FragmentTest(9))

        viewPager2.adapter = CollectVpAdapter(mFragments, mActivity.supportFragmentManager, lifecycle)

        TabLayoutMediator(tabLayout, viewPager2, TabLayoutMediator.TabConfigurationStrategy { tab, position ->
            tab.text = mTitles[position]
        }).attach()
    }

}