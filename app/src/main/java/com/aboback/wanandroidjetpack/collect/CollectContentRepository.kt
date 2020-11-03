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

    suspend fun contentPageApi(page: Int) = withContext(Dispatchers.IO) {
        when (mContentPage) {
            CollectContentPage.COLLECT_ARTICLE -> {
                api.lgCollectList(page)

            }
            CollectContentPage.INTERVIEW_RELATE -> {
                api.articleList(page, 73)
            }
            CollectContentPage.SHARE_ARTICLE -> {
                //页码，从1开始
                var tempPage = page
                val userLgPrivateArticles = api.userLgPrivateArticles(++tempPage)
                userLgPrivateArticles.data?.errorCode = userLgPrivateArticles.errorCode
                userLgPrivateArticles.data?.errorMsg = userLgPrivateArticles.errorMsg
                userLgPrivateArticles.data!!
            }
            else -> {
                ObjectDataBean()
            }
        }
    }


}