package com.aboback.wanandroidjetpack.find.ui

import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.aboback.base.ui.BaseViewModelFragment
import com.aboback.wanandroidjetpack.R
import com.aboback.wanandroidjetpack.collect.SelectPage
import com.aboback.wanandroidjetpack.collect.adapter.CollectVpAdapter
import com.aboback.wanandroidjetpack.find.viewmodel.FindViewModel
import com.aboback.wanandroidjetpack.main.RvScrollToTop
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.youth.banner.transformer.ZoomOutPageTransformer
import kotlinx.android.synthetic.main.fragment_find.*

/**
 * @author jhb
 * @date 2020/10/29
 */
class FindFragment : BaseViewModelFragment<FindViewModel>(R.layout.fragment_find, FindViewModel::class.java), RvScrollToTop {
    private val mFragments = arrayListOf<Fragment>()
    private val mTitles = arrayOf("体系", "公众号", "导航", "项目", "项目分类")

    private var mPagePosition = 0

    override fun onViewInit() {
        super.onViewInit()

        mFragments.add(FindContentTreeAndNaviFragment(FindContentTreeAndNaviPage.TREE))
        mFragments.add(FindContentWeChatFragment())
        mFragments.add(FindContentTreeAndNaviFragment(FindContentTreeAndNaviPage.NAVIGATION))
        mFragments.add(FindContentProjectFragment())
        mFragments.add(FindContentProjectTreeFragment())

        viewPager2.adapter = CollectVpAdapter(mFragments, mActivity.supportFragmentManager, lifecycle)
        viewPager2.setPageTransformer(ZoomOutPageTransformer())
        TabLayoutMediator(tabLayout, viewPager2, TabLayoutMediator.TabConfigurationStrategy { tab, position ->
            tab.text = mTitles[position]
        }).attach()
    }

    override fun onEvent() {
        super.onEvent()
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {}
            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabSelected(tab: TabLayout.Tab?) {
                mPagePosition = tab?.position ?: 0
                viewPager2.setCurrentItem(mPagePosition, false)
            }
        })
        viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                onSelectedPage(position)
            }
        })
    }

    fun onSelectedPage(position: Int) {
        mPagePosition = position
        (mFragments[position] as? SelectPage)?.onSelectPage()
    }

    override fun bindScrollListener() {
        // Nothing
    }

    override fun scrollToTop() {
        (mFragments[mPagePosition] as? RvScrollToTop)?.scrollToTop()
    }

}