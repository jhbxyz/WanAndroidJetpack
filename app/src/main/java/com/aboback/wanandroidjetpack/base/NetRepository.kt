package com.aboback.wanandroidjetpack.base

import com.aboback.base.BaseRepository
import com.aboback.wanandroidjetpack.db.WanDatabase
import com.aboback.wanandroidjetpack.network.WanServer

/**
 * @author jhb
 * @date 2020/10/23
 */
open class NetRepository : BaseRepository {

    val api = WanServer.api

    val db by lazy { WanDatabase.getInstance() }

    private val intervalTime = 1000 * 60 * 60 * 4

    fun Long?.shouldUpdate() = (System.currentTimeMillis() - (this ?: 0L)) > intervalTime


    suspend fun collect(id: Int) = api.collect(id)

    suspend fun unCollect(id: Int, isOnMe: Boolean = false, originId: Int = -1) = if (isOnMe) api.unCollectInMe(id, originId) else api.unCollect(id)


}