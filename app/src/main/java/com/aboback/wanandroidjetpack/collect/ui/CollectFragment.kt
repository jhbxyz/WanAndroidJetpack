package com.aboback.wanandroidjetpack.collect.ui

import android.app.Application
import androidx.fragment.app.Fragment
import com.aboback.base.ui.BaseVMRepositoryFragment
import com.aboback.wanandroidjetpack.R
import com.aboback.wanandroidjetpack.collect.adapter.CollectVpAdapter
import com.aboback.wanandroidjetpack.collect.viewmodel.CollectViewModel
import com.google.android.material.tabs.TabLayoutMediator
import com.youth.banner.transformer.ZoomOutPageTransformer
import kotlinx.android.synthetic.main.fragment_collect.*

/**
 * @author jhb
 * @date 2020/10/29
 */
class CollectFragment : BaseVMRepositoryFragment<CollectViewModel>(R.layout.fragment_collect) {
    private val mFragments = arrayListOf<Fragment>()
    private val mTitles = arrayOf("收藏文章", "面试相关", "分享文章", "收藏网站", "分享项目")

    override fun getViewModel(app: Application) = CollectViewModel(app)

    override fun onViewInit() {
        super.onViewInit()

        mFragments.add(CollectContentFragment(CollectContentPage.COLLECT_ARTICLE))
        mFragments.add(CollectContentFragment(CollectContentPage.INTERVIEW_RELATE))
        mFragments.add(CollectContentFragment(CollectContentPage.SHARE_ARTICLE))
        mFragments.add(CollectContentFragment(CollectContentPage.COLLECT_WEBSITE))
        mFragments.add(CollectContentFragment(CollectContentPage.SHARE_PROJECT))

        viewPager2.adapter = CollectVpAdapter(mFragments, mActivity.supportFragmentManager, lifecycle)
        viewPager2.setPageTransformer(ZoomOutPageTransformer())
        TabLayoutMediator(tabLayout, viewPager2, TabLayoutMediator.TabConfigurationStrategy { tab, position ->
            tab.text = mTitles[position]
        }).attach()
    }

}