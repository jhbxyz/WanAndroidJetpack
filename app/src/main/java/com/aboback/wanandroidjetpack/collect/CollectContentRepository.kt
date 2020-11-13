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

    suspend fun collectArticle(page: Int) = withContext(Dispatchers.IO) {
        api.lgCollectList(page)
    }

    suspend fun interviewRelate(page: Int) = withContext(Dispatchers.IO) {
        api.articleList(page, 73)
    }


    suspend fun shareArticle(page: Int) = withContext(Dispatchers.IO) {
        //页码，从1开始
        var tempPage = page
        api.userLgPrivateArticles(++tempPage)
    }

    suspend fun collectWebsite() = withContext(Dispatchers.IO) {
        api.lgCollectWebsiteList()
    }

    suspend fun shareProject() = withContext(Dispatchers.IO) {

    }


    suspend fun delCollectWebsite(id: Int) = withContext(Dispatchers.IO) {
        api.delCollectWebsite(id)
    }


}