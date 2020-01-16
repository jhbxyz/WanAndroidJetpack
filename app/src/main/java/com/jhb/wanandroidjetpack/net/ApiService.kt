package com.jhb.wanandroidjetpack.net

import com.jhb.wanandroidjetpack.bean.BaseBean
import com.jhb.wanandroidjetpack.login.model.UserLoginBean
import io.reactivex.Observable
import retrofit2.adapter.rxjava2.Result
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

/**
 * Created by jhb on 2020-01-13.
 */
interface ApiService {

    @FormUrlEncoded
    @POST("user/login")
    fun userLogin(
            @Field("username") username: String,
            @Field("password") password: String
    ): Observable<UserLoginBean>

    @FormUrlEncoded
    @POST("user/login")
    fun userLoginResult(
            @Field("username") username: String,
            @Field("password") password: String
    ): Observable<Result<UserLoginBean>>

    @GET("lg/collect/list/0/json")
    fun lgCollectList(): Observable<BaseBean>


}