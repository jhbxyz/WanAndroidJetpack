package com.aboback.wanandroidjetpack.home

import com.aboback.wanandroidjetpack.base.NetRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext

/**
 * @author jhb
 * @date 2020/10/23
 */
class HomeRepository : NetRepository() {


    suspend fun articleList(page: Int) = withContext(Dispatchers.IO) {
        async {
            api.articleList(page)
        }
    }.await()


    suspend fun articleTop() = withContext(Dispatchers.IO) {
        async {
            api.articleTop()
        }
    }.await()


}