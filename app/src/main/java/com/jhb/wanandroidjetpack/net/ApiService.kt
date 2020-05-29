package com.jhb.wanandroidjetpack.net

import com.jhb.wanandroidjetpack.bean.BaseBean
import com.jhb.wanandroidjetpack.bean.WendaListBean
import com.jhb.wanandroidjetpack.login.model.UserLoginBean
import io.reactivex.Observable
import okhttp3.Call
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.adapter.rxjava2.Result
import retrofit2.http.*

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


    @GET("wenda/list/{page}/json ")
    fun wendaList(@Path("page") page: Int): Observable<WendaListBean>


    @GET("wenda/list/{page}/json ")
    fun wendaList2(@Path("page") page: Int): retrofit2.Call<WendaListBean>

    @GET("wenda/list/{page}/json ")
    fun wendaList3(@Path("page") page: Int): retrofit2.Call<ResponseBody>


}