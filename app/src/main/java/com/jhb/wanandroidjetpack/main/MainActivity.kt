package com.jhb.wanandroidjetpack.main

import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.viewpager2.widget.ViewPager2
import com.aboback.ui.BaseViewModelActivity
import com.jhb.wanandroidjetpack.R
import com.jhb.wanandroidjetpack.callback.GlobalSingle
import com.jhb.wanandroidjetpack.home.HomeFragment
import com.jhb.wanandroidjetpack.main.adapter.MainVPAdapter
import com.jhb.wanandroidjetpack.main.viewmodel.MainViewModel
import com.jhb.wanandroidjetpack.util.logE
import com.ke.gson.sdk.ReaderTools
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseViewModelActivity<MainViewModel>(R.layout.activity_main, MainViewModel::class.java) {

    private val mFragments = arrayListOf<Fragment>()

    override fun onViewInit() {
        super.onViewInit()

        initFragment()


        handleErrorGson()

        GlobalSingle.homeNavClick.observe(this, Observer<Int> {
            setPageState(it)
        })

    }

    /**
     * 链家Gson 解析失败,监听回调
     */
    private fun handleErrorGson() {
        ReaderTools.setListener { s, s2 ->
            "s = $s =====  s2 = $s2".logE()
        }

    }

    private fun initFragment() {

//        mFragments.add(CategoryFragment())
        mFragments.add(HomeFragment())
        mFragments.add(Fragment1())
        mFragments.add(Fragment2())
        mFragments.add(Fragment3())
        mFragments.add(Fragment3())


        view_page2.adapter = MainVPAdapter(mFragments, supportFragmentManager, lifecycle)

        view_page2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                setButtonState(position)
            }
        })
    }


    fun setPageState(position: Int) {
        view_page2.currentItem = position
    }

    fun setButtonState(position: Int) {
        when (position) {
            0 -> {
                ll_nav.check(R.id.rb0)
            }
            1 -> {
                ll_nav.check(R.id.rb1)

            }
            2 -> {
                ll_nav.check(R.id.rb2)
            }
            3 -> {
                ll_nav.check(R.id.rb3)
            }
            4 -> {
                ll_nav.check(R.id.rb4)
            }
        }
    }


}




