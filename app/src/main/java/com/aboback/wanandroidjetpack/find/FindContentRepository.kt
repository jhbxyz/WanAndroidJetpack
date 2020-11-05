package com.aboback.wanandroidjetpack.find

import com.aboback.wanandroidjetpack.base.NetRepository
import com.aboback.wanandroidjetpack.bean.ObjectDataBean
import com.aboback.wanandroidjetpack.bean.UserPrivateArticles
import com.aboback.wanandroidjetpack.collect.ui.CollectContentPage
import com.aboback.wanandroidjetpack.find.ui.FindContentPage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * @author jhb
 * @date 2020/10/23
 */
class FindContentRepository(private val mContentPage: FindContentPage) : NetRepository() {

    suspend fun collectArticle(page: Int) = withContext(Dispatchers.IO) {
        api.lgCollectList(page)
    }


}