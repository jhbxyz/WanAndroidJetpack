package com.aboback.wanandroidjetpack.find

import com.aboback.wanandroidjetpack.base.NetRepository

/**
 * @author jhb
 * @date 2020/11/17
 */
class TreeListRepository : NetRepository() {

    suspend fun articleList(page: Int, cid: Int?) = api.articleList(page, cid)

}