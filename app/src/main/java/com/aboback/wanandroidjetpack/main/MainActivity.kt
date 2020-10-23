package com.aboback.wanandroidjetpack.main

import androidx.lifecycle.Observer
import androidx.viewpager2.widget.ViewPager2
import com.aboback.base.ui.BaseViewModelActivity
import com.aboback.wanandroidjetpack.R
import com.aboback.wanandroidjetpack.main.adapter.MainVPAdapter
import com.aboback.wanandroidjetpack.main.viewmodel.MainViewModel
import com.aboback.wanandroidjetpack.util.logE
import com.ke.gson.sdk.ReaderTools
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseViewModelActivity<MainViewModel>(R.layout.activity_main, MainViewModel::class.java) {

    private val mFragments = arrayListOf<Fragment>()

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
        mFragments.add(Fragment(1))
        mFragments.add(Fragment(2))
        mFragments.add(Fragment(3))
        mFragments.add(Fragment(4))
        mFragments.add(Fragment(5))


        viewPager2.adapter = MainVPAdapter(mFragments, supportFragmentManager, lifecycle)

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




