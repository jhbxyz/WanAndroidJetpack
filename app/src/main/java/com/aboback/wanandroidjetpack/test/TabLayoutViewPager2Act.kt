package com.aboback.wanandroidjetpack.test

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.aboback.base.ui.BaseActivity
import com.aboback.base.util.logWithTag
import com.aboback.wanandroidjetpack.R
import com.aboback.wanandroidjetpack.collect.SelectPage
import com.aboback.wanandroidjetpack.collect.adapter.CollectVpAdapter
import com.aboback.wanandroidjetpack.main.ui.FragmentTest
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.tablayout_viewpager2_test.*

/**
 * @author jhb
 * @date 2020/11/13
 */
class TabLayoutViewPager2Act : BaseActivity() {
    private val mFragments = arrayListOf<Fragment>()
    private val mTitles = arrayOf("体系", "导航", "公众号", "项目", "项目分类")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tablayout_viewpager2_test)
        if (mFragments.isNotEmpty()) {
            mFragments.clear()
        }
        mFragments.add(FragmentTest(0))
        mFragments.add(FragmentTest(1))
        mFragments.add(FragmentTest(2))
        mFragments.add(FragmentTest(3))
        mFragments.add(FragmentTest(4))

        viewPager2.adapter = CollectVpAdapter(mFragments, supportFragmentManager, lifecycle)
        TabLayoutMediator(tabLayout, viewPager2, TabLayoutMediator.TabConfigurationStrategy { tab, position ->
            tab.text = mTitles[position]
        }).attach()

//        viewPager2.setCurrentItem(2)
//        tabLayout.getTabAt(3)?.select()
//        onSelectedPage(0)


        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {}
            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabSelected(tab: TabLayout.Tab?) {
                val position = tab?.position ?: 0
                "onTabSelected = $position".logWithTag("222222")
                viewPager2.setCurrentItem(position, false)
            }
        })
        viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                "onPageSelected = $position".logWithTag("222222")
                onSelectedPage(position)
            }
        })


    }

    fun onSelectedPage(position: Int) {
        (mFragments[position] as? SelectPage)?.onSelectPage()
    }
}