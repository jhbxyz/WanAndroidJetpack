package com.aboback.wanandroidjetpack.find

import com.aboback.wanandroidjetpack.base.NetRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * @author jhb
 * @date 2020/10/23
 */
class FindContentProjectTreeRepository : NetRepository() {

    suspend fun projectTreeList() = api.projectTreeList()

    suspend fun projectListCid(page: Int, cid: Int?) = api.projectListCid(page, cid)


}