package com.aboback.wanandroidjetpack.collect.ui

import androidx.viewpager2.widget.ViewPager2
import com.aboback.base.ui.BaseViewModelFragment
import com.aboback.base.util.logWithTag
import com.aboback.wanandroidjetpack.R
import com.aboback.wanandroidjetpack.collect.SelectPage
import com.aboback.wanandroidjetpack.collect.adapter.CollectVpAdapter
import com.aboback.wanandroidjetpack.collect.viewmodel.CollectViewModel
import com.aboback.wanandroidjetpack.main.RvScrollToTop
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.youth.banner.transformer.ZoomOutPageTransformer
import kotlinx.android.synthetic.main.fragment_collect.*

/**
 * @author jhb
 * @date 2020/10/29
 */
class CollectFragment : BaseViewModelFragment<CollectViewModel>(R.layout.fragment_collect, CollectViewModel::class.java), RvScrollToTop {
    private val mFragments = arrayListOf<CollectContentFragment>()
    private val mTitles = arrayOf("收藏文章", "面试相关", "分享文章", "收藏网站"/*, "分享项目"*/)

    private var mPagePosition = 0

    override fun onViewInit() {
        super.onViewInit()

        if (mFragments.isNotEmpty()) {
            mFragments.clear()
        }
        mFragments.add(CollectContentFragment(CollectContentPage.COLLECT_ARTICLE))
        mFragments.add(CollectContentFragment(CollectContentPage.INTERVIEW_RELATE))
        mFragments.add(CollectContentFragment(CollectContentPage.SHARE_ARTICLE))
        mFragments.add(CollectContentFragment(CollectContentPage.COLLECT_WEBSITE))
//        mFragments.add(CollectContentFragment(CollectContentPage.SHARE_PROJECT))

        viewPager2.adapter = CollectVpAdapter(mFragments, childFragmentManager, lifecycle)
//        viewPager2.setPageTransformer(ZoomOutPageTransformer())
        TabLayoutMediator(tabLayout, viewPager2, TabLayoutMediator.TabConfigurationStrategy { tab, position ->
            tab.text = mTitles[position]
        }).attach()
        onSelectedPage(0)
    }

    override fun onEvent() {
        super.onEvent()
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {}
            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabSelected(tab: TabLayout.Tab?) {
                mPagePosition = tab?.position ?: 0
                viewPager2.setCurrentItem(mPagePosition, false)
                onSelectedPage(mPagePosition)
            }
        })
//        viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
//            override fun onPageSelected(position: Int) {
//                super.onPageSelected(position)
//                onSelectedPage(position)
//            }
//        })
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