package com.aboback.wanandroidjetpack.collect

import com.aboback.wanandroidjetpack.base.NetRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext

/**
 * @author jhb
 * @date 2020/10/23
 */
class CollectRepository : NetRepository() {


    suspend fun banner() = withContext(Dispatchers.Default) {
        async {
            api.banner()
        }
    }.await()



}