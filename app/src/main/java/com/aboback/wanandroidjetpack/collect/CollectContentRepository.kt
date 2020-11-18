package com.aboback.wanandroidjetpack.collect

import com.aboback.wanandroidjetpack.base.NetRepository
import com.aboback.wanandroidjetpack.bean.ObjectDataBean
import com.aboback.wanandroidjetpack.bean.UserPrivateArticles
import com.aboback.wanandroidjetpack.collect.ui.CollectContentPage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * @author jhb
 * @date 2020/10/23
 */
class CollectContentRepository(private val mContentPage: CollectContentPage) : NetRepository() {

    suspend fun collectArticle(page: Int) = api.lgCollectList(page)

    suspend fun interviewRelate(page: Int) = api.articleList(page, 73)


    suspend fun shareArticle(page: Int): UserPrivateArticles {
        //页码，从1开始
        var tempPage = page
        return api.userLgPrivateArticles(++tempPage)
    }

    suspend fun collectWebsite() = api.lgCollectWebsiteList()

    suspend fun shareProject() = withContext(Dispatchers.IO) {

    }

    suspend fun delCollectWebsite(id: Int) = api.delCollectWebsite(id)

    suspend fun delMyArticle(id: Int?) = api.delMyArticle(id)


}