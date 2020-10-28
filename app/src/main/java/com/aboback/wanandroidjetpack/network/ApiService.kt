package com.aboback.wanandroidjetpack.network

import com.aboback.wanandroidjetpack.bean.*
import com.aboback.wanandroidjetpack.login.model.UserLoginBean
import io.reactivex.Flowable
import retrofit2.adapter.rxjava2.Result
import retrofit2.http.*

/**
 * Created by jhb on 2020-01-13.
 */
interface ApiService {

    @GET("/banner/json")
    suspend fun banner(): BannerBean

    @GET("/article/top/json")
    suspend fun articleTop(): ArticleTopBean

    @GET("/article/list/{page}/json")
    suspend fun articleList(
            @Path("page") page: Int,
            @Query("cid") cid: Int? = null//73为面试的cid
    ): ArticleListBean

    @GET("wenda/list/{page}/json ")
    suspend fun wendaList(@Path("page") page: Int): WendaListBean

    @FormUrlEncoded
    @POST("user/login")
    suspend fun userLogin(
            @Field("username") username: String?,
            @Field("password") password: String?
    ): Result<UserLoginBean>

    @GET("lg/collect/list/0/json")
    suspend fun lgCollectList(): BaseBean

    @GET("tree/json")
    suspend fun treeJson(): TreeJsonBean


}