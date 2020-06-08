package com.jhb.wanandroidjetpack.net

import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by jhb on 2020-01-13.
 */
object WanService {

    private const val READ_TIMEOUT = 60L
    private const val WRITE_TIMEOUT = 60L
    private const val CONNECT_TIMEOUT = 30L

    private const val BASE_URL = "https://www.wanandroid.com/"

    private var mRetrofit: Retrofit? = null

    private fun getClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .retryOnConnectionFailure(true)
            .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor(CookieInterceptor())
            .build()
    }

    private fun getRetrofit(): Retrofit {
        return mRetrofit ?: Retrofit.Builder()
            .baseUrl(BASE_URL)
            .validateEagerly(true)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .client(getClient())
            .build()
            .also { mRetrofit = it }

    }

    var api = getRetrofit().create(ApiService::class.java)

}