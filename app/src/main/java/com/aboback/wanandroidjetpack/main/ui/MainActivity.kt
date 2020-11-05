package com.aboback.wanandroidjetpack.main.ui

import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.viewpager2.widget.ViewPager2
import com.aboback.base.util.log
import com.aboback.base.ui.BaseViewModelActivity
import com.aboback.wanandroidjetpack.R
import com.aboback.wanandroidjetpack.collect.ui.CollectFragment
import com.aboback.wanandroidjetpack.find.ui.FindFragment
import com.aboback.wanandroidjetpack.home.ui.HomeFragment
import com.aboback.wanandroidjetpack.main.RvScrollToTop
import com.aboback.wanandroidjetpack.main.adapter.MainVPAdapter
import com.aboback.wanandroidjetpack.main.viewmodel.MainViewModel
import com.aboback.wanandroidjetpack.me.MeFragment
import com.aboback.wanandroidjetpack.wenda.WenDaFragment
import com.ke.gson.sdk.ReaderTools
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseViewModelActivity<MainViewModel>(R.layout.activity_main, MainViewModel::class.java) {

    private val mFragments = arrayListOf<Fragment>()
    private var mPagePosition = 0

    override fun onViewInit() {
        super.onViewInit()

        initFragment()


    }

    override fun onEvent() {
        super.onEvent()

        handleErrorGson()

        mRealVM.mHomeNavClick.observe(this, Observer<Int> {
            setPageState(it)
        })
        mRealVM.mFabClick.observe(this, Observer {
            if (it) {
                (mFragments[mPagePosition] as? RvScrollToTop)?.scrollToTop()
                mRealVM.mFabClick.value = false
            }
        })

    }

    /**
     * 链家Gson 解析失败,监听回调
     */
    private fun handleErrorGson() {
        ReaderTools.setListener { s, s2 ->
            "ReaderTools  s = $s =====  s2 = $s2".log()
        }

    }

    private fun initFragment() {

        mFragments.add(HomeFragment())
        mFragments.add(WenDaFragment())
        mFragments.add(CollectFragment())
        mFragments.add(FindFragment())
        mFragments.add(MeFragment())


        viewPager2.adapter = MainVPAdapter(mFragments, supportFragmentManager, lifecycle)
        viewPager2.isUserInputEnabled = false
        viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                setButtonState(position)
            }
        })
    }


    fun setPageState(position: Int) {
        viewPager2.setCurrentItem(position, false)
    }

    fun setButtonState(position: Int) {
        mRealVM.mFabVisible.set(false)
        mPagePosition = position
        when (position) {
            0 -> {
                llNav.check(R.id.rb0)
            }
            1 -> {
                llNav.check(R.id.rb1)

            }
            2 -> {
                llNav.check(R.id.rb2)
            }
            3 -> {
                llNav.check(R.id.rb3)
            }
            4 -> {
                llNav.check(R.id.rb4)
            }
        }
    }


}




