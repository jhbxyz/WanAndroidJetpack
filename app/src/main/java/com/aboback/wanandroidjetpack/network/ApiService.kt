package com.aboback.wanandroidjetpack.network

import com.aboback.wanandroidjetpack.bean.*
import com.aboback.wanandroidjetpack.login.model.UserLoginBean
import retrofit2.adapter.rxjava2.Result
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


    @FormUrlEncoded
    @POST("user/login")
    suspend fun userLogin(
            @Field("username") username: String?,
            @Field("password") password: String?
    ): Result<UserLoginBean>

    @GET("tree/json")
    suspend fun treeJson(): TreeJsonBean


}