package com.aboback.wanandroidjetpack.find

import com.aboback.wanandroidjetpack.base.NetRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * @author jhb
 * @date 2020/10/23
 */
class FindContentProjectTreeRepository() : NetRepository() {

    suspend fun projectList(page: Int) = withContext(Dispatchers.IO) {
        api.projectList(page)
    }


    suspend fun weChatListDetail(id: Int?, page: Int) = withContext(Dispatchers.IO) {
        api.weChatListDetail(id, page)
    }


}