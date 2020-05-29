package com.jhb.wanandroidjetpack.test.okhttp_demo

import com.jhb.wanandroidjetpack.util.logE
import com.jhb.wanandroidjetpack.util.logEWhitT
import okhttp3.OkHttpClient
import okhttp3.Request
import okio.BufferedSource
import okio.Okio
import java.io.InputStream
import kotlin.concurrent.thread


/**
 * @author jhb
 * @date 2020/5/21
 */
object SyncHttpRequest {

    fun syncRequest() {


        thread {

            val client = OkHttpClient()

            val request = Request.Builder()
                .get()
                .url("https://wanandroid.com/wxarticle/chapters/json")
                .build()
            val call = client.newCall(request)
            val response = call.execute()
            response.body?.string()?.logEWhitT("SyncHttpRequest")

        }

    }
}