package com.aboback.wanandroidjetpack.collect

import com.aboback.wanandroidjetpack.base.NetRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext

/**
 * @author jhb
 * @date 2020/10/23
 */
class CollectContentRepository : NetRepository() {


    suspend fun wendaList(page: Int) = withContext(Dispatchers.IO) {
        api.wendaList(page)
    }


}