package com.jhb.wanandroidjetpack.util

import android.os.Handler
import android.os.Looper
import java.util.concurrent.Executor
import java.util.concurrent.Executors

/**
 * Created by jhb on 2020-01-14.
 */
object WanExecutors {

    val mDiskIO = Executors.newSingleThreadExecutor()
    val mNetworkIO = Executors.newFixedThreadPool(3)
    val mMainThread = MainThreadExecutor()


    class MainThreadExecutor(var time: Long = 0) : Executor {
        private val mHandle = Handler(Looper.getMainLooper())
        override fun execute(command: Runnable) {
            mHandle.postDelayed(command, time)
        }
    }


}