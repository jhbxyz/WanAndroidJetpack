package com.aboback.wanandroidjetpack.find

import com.aboback.wanandroidjetpack.base.NetRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * @author jhb
 * @date 2020/10/23
 */
class FindContentWeChatRepository : NetRepository() {

    suspend fun weChatList() = api.weChatList()

    suspend fun weChatListDetail(id: Int?, page: Int) = api.weChatListDetail(id, page)


}