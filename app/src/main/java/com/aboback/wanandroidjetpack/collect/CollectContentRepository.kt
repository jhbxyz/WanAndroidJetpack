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


    suspend fun wendaList(page: Int) = withContext(Dispatchers.IO) {
        api.wendaList(page)
    }


}