package com.jhb.wanandroidjetpack.net

import android.content.Context
import com.jhb.wanandroidjetpack.base.WanApp
import com.jhb.wanandroidjetpack.util.logE
import okhttp3.Interceptor
import okhttp3.Response

/**
 * Created by jhb on 2020-01-16.
 */
class CookieInterceptor : Interceptor {

    companion object {
        private const val KEY_COOKIE = "cookie"
    }

    override fun intercept(chain: Interceptor.Chain): Response {

        val finalResponse: Response

        val sp = WanApp.instance.getSharedPreferences("cookie_config", Context.MODE_PRIVATE)

        val stringSet = sp.getStringSet(KEY_COOKIE, null)

        if (stringSet.isNullOrEmpty()) {
            val originResponse = chain.proceed(chain.request())

            if (!originResponse.headers("Set-Cookie").isNullOrEmpty()) {

                val cookies = hashSetOf<String>()
                originResponse.headers("Set-Cookie").forEach {
                    cookies.add(it)
                }

                sp.edit().putStringSet(KEY_COOKIE, cookies).apply()
            }

            finalResponse = originResponse

        } else {
            val builder = chain.request().newBuilder()
            stringSet.forEach {
                builder.addHeader("Cookie", it)
            }
            finalResponse = chain.proceed(builder.build())
        }

        return finalResponse
    }

}