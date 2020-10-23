package com.aboback.wanandroidjetpack.network

import com.aboback.wanandroidjetpack.bean.ArticleListBean
import com.aboback.wanandroidjetpack.bean.BaseBean
import com.aboback.wanandroidjetpack.bean.TreeJsonBean
import com.aboback.wanandroidjetpack.bean.WendaListBean
import com.aboback.wanandroidjetpack.login.model.UserLoginBean
import io.reactivex.Flowable
import retrofit2.adapter.rxjava2.Result
import retrofit2.http.*

/**
 * Created by jhb on 2020-01-13.
 */
interface ApiService {

    @FormUrlEncoded
    @POST("user/login")
    fun userLogin(
            @Field("username") username: String?,
            @Field("password") password: String?
    ): Flowable<Result<UserLoginBean>>


    @GET("lg/collect/list/0/json")
    fun lgCollectList(): Flowable<BaseBean>

    @GET("tree/json")
    fun treeJson(): Flowable<TreeJsonBean>


    @GET("/article/list/{page}/json")
    suspend fun articleList(
            @Path("page") page: Int,
            @Query("cid") cid: Int? = null//73为面试的cid
    ): ArticleListBean

    @GET("wenda/list/{page}/json ")
    fun wendaList(@Path("page") page: Int): Flowable<WendaListBean>


}