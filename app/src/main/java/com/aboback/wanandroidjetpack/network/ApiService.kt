package com.aboback.wanandroidjetpack.network

import com.aboback.network.BaseBean
import com.aboback.wanandroidjetpack.bean.*
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

/**
 * Created by jhb on 2020-01-13.
 */
interface ApiService {

    @GET("/banner/json")
    suspend fun banner(): ArrayDataBean

    @GET("/article/top/json")
    suspend fun articleTop(): ArrayDataBean

    @GET("/article/list/{page}/json")
    suspend fun articleList(
            @Path("page") page: Int,
            @Query("cid") cid: Int? = null//73为面试的cid
    ): ObjectDataBean

    @GET("wenda/list/{page}/json ")
    suspend fun wendaList(@Path("page") page: Int): ObjectDataBean

    @GET("lg/collect/list/{page}/json")
    suspend fun lgCollectList(@Path("page") page: Int): ArrayDataBean


    @GET("lg/coin/userinfo/json")
    suspend fun lgCoinUserInfo(): CoinUserInfoBean


    @FormUrlEncoded
    @POST("user/login")
    suspend fun userLogin(
            @Field("username") username: String?,
            @Field("password") password: String?
    ): Response<UserLoginBean?>?

    @GET("/user/logout/json")
    suspend fun userLogout(): BaseBean

    @GET("tree/json")
    suspend fun treeJson(): TreeJsonBean


}