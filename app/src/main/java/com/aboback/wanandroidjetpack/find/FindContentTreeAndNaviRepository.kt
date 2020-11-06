package com.aboback.wanandroidjetpack.find

import com.aboback.wanandroidjetpack.base.NetRepository
import com.aboback.wanandroidjetpack.bean.ObjectDataBean
import com.aboback.wanandroidjetpack.bean.UserPrivateArticles
import com.aboback.wanandroidjetpack.collect.ui.CollectContentPage
import com.aboback.wanandroidjetpack.find.ui.FindContentPage
import com.aboback.wanandroidjetpack.find.ui.FindContentTreeAndNaviPage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * @author jhb
 * @date 2020/10/23
 */
class FindContentTreeAndNaviRepository(private val mContentPage: FindContentTreeAndNaviPage) : NetRepository() {


    suspend fun treeList() = withContext(Dispatchers.IO) {
        api.treeList()
    }



    suspend fun naviList() = withContext(Dispatchers.IO) {
        api.naviList()
    }


}