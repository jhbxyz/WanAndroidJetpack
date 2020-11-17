package com.aboback.wanandroidjetpack.collect.ui

import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.aboback.base.ui.BaseViewModelFragment
import com.aboback.base.util.logWithTag
import com.aboback.wanandroidjetpack.R
import com.aboback.wanandroidjetpack.bridge.GlobalSingle
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

    private fun initFragment(page: CollectContentPage): CollectContentFragment {
        val collectContentFragment = CollectContentFragment()
        val bundle = Bundle()
        bundle.putSerializable(CollectContentFragment.CONTENT_PAGE, page)
        collectContentFragment.arguments = bundle
        return collectContentFragment
    }

    override fun onViewInit() {
        super.onViewInit()

        if (mFragments.isNotEmpty()) {
            mFragments.clear()
        }

        mFragments.add(initFragment(CollectContentPage.COLLECT_ARTICLE))
        mFragments.add(initFragment(CollectContentPage.INTERVIEW_RELATE))
        mFragments.add(initFragment(CollectContentPage.SHARE_ARTICLE))
        mFragments.add(initFragment(CollectContentPage.COLLECT_WEBSITE))
//        mFragments.add(CollectContentFragment(CollectContentPage.SHARE_PROJECT))

        viewPager2.adapter = CollectVpAdapter(mFragments, childFragmentManager, lifecycle)
        viewPager2.setPageTransformer(ZoomOutPageTransformer())
        TabLayoutMediator(tabLayout, viewPager2, TabLayoutMediator.TabConfigurationStrategy { tab, position ->
            tab.text = mTitles[position]
        }).attach()

        onSelectedPage(sIndex)
    }

    override fun onEvent() {
        super.onEvent()
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {}
            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabSelected(tab: TabLayout.Tab?) {
                viewPager2.setCurrentItem(tab?.position ?: 0, false)
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
        sIndex = position
        GlobalSingle.onCollectPageSelect.value = getPageByIndex(sIndex)
    }

    override fun bindScrollListener() {
        // Nothing
    }

    override fun scrollToTop() {
        (mFragments[viewPager2.currentItem] as? RvScrollToTop)?.scrollToTop()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        "onDestroyView  ".logWithTag(mTag)
        try {
            val mRv = viewPager2::class.java.getDeclaredField("mRecyclerView")
            mRv.isAccessible = true
            (mRv as? RecyclerView)?.recycledViewPool?.clear()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}