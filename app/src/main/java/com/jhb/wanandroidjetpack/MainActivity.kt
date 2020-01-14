package com.jhb.wanandroidjetpack

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jhb.wanandroidjetpack.bean.BaseBean
import com.jhb.wanandroidjetpack.net.WanObserver
import com.jhb.wanandroidjetpack.net.WanService
import io.reactivex.BackpressureStrategy
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.ResourceObserver

class MainActivity : AppCompatActivity() {

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        WanService.api
            .userLogin("jhb", "124")
            .toFlowable(BackpressureStrategy.DROP)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : WanObserver<BaseBean>() {
                override fun onSuccess(t: BaseBean) {

                }
            })
    }

}




