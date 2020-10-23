package com.jhb.wanandroidjetpack.main

import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.viewpager2.widget.ViewPager2
import com.aboback.base.ui.BaseViewModelActivity
import com.jhb.wanandroidjetpack.R
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
        mFragments.add(com.jhb.wanandroidjetpack.main.Fragment(1))
        mFragments.add(com.jhb.wanandroidjetpack.main.Fragment(2))
        mFragments.add(com.jhb.wanandroidjetpack.main.Fragment(3))
        mFragments.add(com.jhb.wanandroidjetpack.main.Fragment(4))
        mFragments.add(com.jhb.wanandroidjetpack.main.Fragment(5))


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




