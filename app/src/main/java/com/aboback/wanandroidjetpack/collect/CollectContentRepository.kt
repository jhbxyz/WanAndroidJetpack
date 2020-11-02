package com.aboback.wanandroidjetpack.collect

import com.aboback.wanandroidjetpack.base.NetRepository
import com.aboback.wanandroidjetpack.collect.ui.CollectContentPage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
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
            else -> {
                api.lgCollectList(page)
            }
        }
    }


}