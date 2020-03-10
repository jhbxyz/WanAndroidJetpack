package com.jhb.wanandroidjetpack.test

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jhb.wanandroidjetpack.R
import com.jhb.wanandroidjetpack.util.GalleryTransFormer
import kotlinx.android.synthetic.main.activity_smart_tablayout.*

/**
 * Created by jhb on 2020-03-03.
 */
class SmartTabLayoutActivity : AppCompatActivity() {

    val s = "https://images.unsplash.com/photo-1518020382113-a7e8fc38eac9?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=800&q=60"
    val s2 = "https://images.unsplash.com/photo-1515536765-9b2a70c4b333?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=800&q=60"

    val s3 = "https://images.unsplash.com/photo-1507808973436-a4ed7b5e87c9?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=800&q=60"

    val mList = arrayListOf<String>()
    val mTitle = arrayListOf<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_smart_tablayout)

        mList.add(s)
        mList.add(s2)
        mList.add(s3)

        mTitle.add("首页你好")
        mTitle.add("滴滴无敌")
        mTitle.add("测试哈哈")

        vp.offscreenPageLimit = 3
        vp.pageMargin = 20
        vp.adapter = MyVpAdapter(mList,mTitle)
        vp.setPageTransformer(true, GalleryTransFormer())
        stl.setViewPager(vp)
    }
}