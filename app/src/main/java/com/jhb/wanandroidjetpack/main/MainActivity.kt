package com.jhb.wanandroidjetpack.main

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.viewpager2.widget.ViewPager2
import com.jhb.wanandroidjetpack.R
import com.jhb.wanandroidjetpack.base.BaseSimpleVMActivity
import com.jhb.wanandroidjetpack.databinding.ActivityMainBinding
import com.jhb.wanandroidjetpack.main.adapter.MainVPAdapter
import com.jhb.wanandroidjetpack.main.viewmodel.MainVM
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseSimpleVMActivity<MainVM>(R.layout.activity_main, MainVM()) {

    private val mFragments = arrayListOf<Fragment>()

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mRealVM

        initFragment()

        mShareViewModel.homeNavClick.observe(this, Observer<Int> {
            setPageState(it)
        })
    }

    private fun initFragment() {

        mFragments.add(Fragment1())
        mFragments.add(Fragment2())
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
        }
    }


}




