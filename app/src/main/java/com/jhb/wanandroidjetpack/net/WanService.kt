package com.jhb.wanandroidjetpack.net

import com.google.gson.Gson
import com.google.gson.GsonBuilder
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


    private const val BASE_URL = "https://www.wanandroid.com/"

    private const val READ_TIME = 60L
    private const val WRITE_TIME = 60L
    private const val CONNECT_TIME = 30L

    private var mClient = OkHttpClient.Builder()
        .readTimeout(READ_TIME, TimeUnit.SECONDS)
        .writeTimeout(WRITE_TIME, TimeUnit.SECONDS)
        .connectTimeout(CONNECT_TIME, TimeUnit.SECONDS)
        .addInterceptor(CookieInterceptor())
        .build()

    private var mService: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
        .addConverterFactory(GsonConverterFactory.create())
        .client(mClient)
        .build()

    @Deprecated("改用库 https://github.com/LianjiaTech/gson-plugin")
    private fun getGson(): Gson {
        return GsonBuilder()
            .registerTypeHierarchyAdapter(List::class.java, ArraySecurityAdapter())
            .registerTypeHierarchyAdapter(Int::class.java, IntDefaultAdapter())
            .registerTypeHierarchyAdapter(Any::class.java, ObjectSecurityAdapter())
            .create()
    }

    var api = mService.create(ApiService::class.java)


}