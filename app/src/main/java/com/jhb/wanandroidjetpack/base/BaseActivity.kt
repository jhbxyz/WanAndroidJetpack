package com.jhb.wanandroidjetpack.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jhb.wanandroidjetpack.util.ActivityUtil

/**
 * Created by jhb on 2020-01-14.
 */
open class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivityUtil.addActivity(this)
    }


    override fun onDestroy() {
        super.onDestroy()
        ActivityUtil.removeActivity(this)

    }


}