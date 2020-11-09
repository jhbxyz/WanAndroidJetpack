package com.aboback.wanandroidjetpack.find

import com.aboback.wanandroidjetpack.base.NetRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * @author jhb
 * @date 2020/10/23
 */
class FindContentProjectRepository : NetRepository() {

    suspend fun projectList(page: Int) = withContext(Dispatchers.IO) {
        api.projectList(page)
    }


}