package com.aboback.wanandroidjetpack.network

import com.aboback.network.BaseBean
import com.aboback.wanandroidjetpack.bean.*
import retrofit2.Response
import retrofit2.http.*

/**
 * Created by jhb on 2020-01-13.
 */
interface ApiService {

    //首页banner
    @GET("/banner/json")
    suspend fun banner(): ArrayDataBean

    //置顶文章
    @GET("/article/top/json")
    suspend fun articleTop(): ArrayDataBean

    /**
     *  1.首页文章列表
     *  2.知识体系下的文章  cid 分类的id，上述二级目录的id
     */
    @GET("/article/list/{page}/json")
    suspend fun articleList(
            @Path("page") page: Int,
            @Query("cid") cid: Int? = null//73为面试的cid
    ): ObjectDataBean

    //问答
    @GET("wenda/list/{page}/json ")
    suspend fun wendaList(@Path("page") page: Int): ObjectDataBean

    //自己收藏文章列表
    @GET("lg/collect/list/{page}/json")
    suspend fun lgCollectList(@Path("page") page: Int): ObjectDataBean


    //收藏站内文章 文章id，拼接在链接中。
    @POST("/lg/collect/{id}/json")
    suspend fun collect(@Path("id") id: Int?): BaseBean

    //取消收藏  文章列表 文章id，拼接在链接中。
    @POST("lg/uncollect_originId/{id}/json")
    suspend fun unCollect(@Path("id") id: Int?): BaseBean

    /**
     * 取消收藏
     * 1.文章列表 id:拼接在链接上
     * 2.我的收藏页面（该页面包含自己录入的内容）id:拼接在链接上  originId:列表页下发，无则为-1
     */
    @FormUrlEncoded
    @POST("/lg/uncollect/{id}/json")
    suspend fun unCollectInMe(@Path("id") id: Int, @Field("originId") originId: Int): BaseBean

    //获取个人积分，需要登录后访问
    @GET("lg/coin/userinfo/json")
    suspend fun lgCoinUserInfo(): CoinUserInfoBean

    //收藏网站列表
    @GET("/lg/collect/usertools/json")
    suspend fun lgCollectWebsiteList(): ArrayDataBean

    //登录 username  password
    @FormUrlEncoded
    @POST("user/login")
    suspend fun userLogin(
            @Field("username") username: String?,
            @Field("password") password: String?
    ): Response<UserLoginBean?>?

    //注册 username password repassword
    @FormUrlEncoded
    @POST("/user/register")
    suspend fun userRegister(
            @Field("username") username: String?,
            @Field("password") password: String?,
            @Field("repassword") repassword: String?
    ): Response<UserLoginBean?>?

    //退出
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

    //积分排行榜接口
    @GET("/coin/rank/{page}/json")
    suspend fun coinRankList(@Path("page") page: Int): ObjectDataBean

    //收藏站外文章 title，author，link
    @FormUrlEncoded
    @POST("/lg/collect/add/json")
    suspend fun collectAdd(
            @Field("title") title: String?,
            @Field("author") author: String?,
            @Field("link") link: String?
    ): BaseBean

    //收藏网址  name,link
    @FormUrlEncoded
    @POST("lg/collect/addtool/json")
    suspend fun addCollectWebsite(
            @Field("name") name: String?,
            @Field("link") link: String?
    ): BaseBean

    //编辑收藏网站  id,name,link  lg/collect/addtool/json
    @FormUrlEncoded
    @POST("/lg/collect/updatetool/json")
    suspend fun updateCollectWebsite(
            @Field("id") id: Int?,
            @Field("name") name: String?,
            @Field("link") link: String?
    ): BaseBean

    //删除收藏网站  id
    @FormUrlEncoded
    @POST("lg/collect/deletetool/json")
    suspend fun delCollectWebsite(@Field("id") id: Int): BaseBean

    //自己分享的文章
    @GET("/user/lg/private_articles/{page}/json")
    suspend fun userLgPrivateArticles(@Path("page") page: Int): UserPrivateArticles


    //删除自己分享的文章  id
    @POST("lg/user_article/delete/{id}/json")
    suspend fun delMyArticle(@Path("id") id: Int?): BaseBean

    //分享文章
    @FormUrlEncoded
    @POST("/lg/user_article/add/json")
    suspend fun addMyArticle(@Field("title") title: String?,
                             @Field("link") link: String?
    ): BaseBean


}