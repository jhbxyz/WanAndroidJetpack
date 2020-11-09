package com.aboback.wanandroidjetpack.network

import com.aboback.network.BaseBean
import com.aboback.wanandroidjetpack.bean.*
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

    //收藏文章
    @GET("lg/collect/list/{page}/json")
    suspend fun lgCollectList(@Path("page") page: Int): ObjectDataBean


    @GET("lg/coin/userinfo/json")
    suspend fun lgCoinUserInfo(): CoinUserInfoBean

    //收藏网站列表
    @GET("/lg/collect/usertools/json")
    suspend fun lgCollectWebsiteList(): ArrayDataBean


    @FormUrlEncoded
    @POST("user/login")
    suspend fun userLogin(
            @Field("username") username: String?,
            @Field("password") password: String?
    ): Response<UserLoginBean?>?

    @GET("/user/logout/json")
    suspend fun userLogout(): BaseBean

    //获取公众号列表
    @GET("/wxarticle/chapters/json")
    suspend fun weChatList(): WeChatListBean


    //查看某个公众号历史数据
    @GET("/wxarticle/list/{id}/{page}/json")
    suspend fun weChatListDetail(
            @Path("id") id: Int?,
            @Path("page") page: Int
    ): ObjectDataBean

    //体系数据
    @GET("tree/json")
    suspend fun treeList(): TreeListBean

    //导航数据
    @GET("/navi/json")
    suspend fun naviList(): NaviListBean

    //项目(热门项目)
    @GET("/article/listproject/{page}/json")
    suspend fun projectList(@Path("page") page: Int): ObjectDataBean

    //项目分类
    @GET("/project/tree/json")
    suspend fun projectTreeList(): ArrayDataBean

    //项目分类
    @GET("/project/list/{page}/json")
    suspend fun projectListCid(@Path("page") page: Int,
                               @Query("cid") cid: Int? = null): ObjectDataBean

    //自己分享的文章
    @GET("/user/lg/private_articles/{page}/json")
    suspend fun userLgPrivateArticles(@Path("page") page: Int): UserPrivateArticles


}