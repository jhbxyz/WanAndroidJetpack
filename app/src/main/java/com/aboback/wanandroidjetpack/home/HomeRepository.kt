package com.aboback.wanandroidjetpack.home

import com.aboback.base.util.isNotNull
import com.aboback.base.util.isNull
import com.aboback.wanandroidjetpack.base.NetRepository
import com.aboback.wanandroidjetpack.home.viewmodel.HomePageState
import com.aboback.wanandroidjetpack.util.netError
import com.aboback.wanandroidjetpack.util.noMoreData
import com.aboback.wanandroidjetpack.util.response
import com.blankj.utilcode.util.NetworkUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext

/**
 * @author jhb
 * @date 2020/10/23
 */
class HomeRepository : NetRepository() {


    private val bannerDao = db.bannerDataDao
    private val arrayDao = db.arrayDataDao
    private val objectDao = db.objectDataDao

    suspend fun banner() = withContext(Dispatchers.Default) {
        async {
            var bannerDataBean = bannerDao.getDataBean()
            if (bannerDataBean.isNull() || bannerDataBean?.mLastTime.shouldUpdate()) {
                response(api.banner()) {
                    bannerDataBean = this
                    bannerDataBean?.mLastTime = System.currentTimeMillis()
                    bannerDao.insert(bannerDataBean)
                }
            }
            bannerDataBean
        }
    }.await()

    suspend fun articleTop() = withContext(Dispatchers.Default) {
        async {
            var arrayDataBean = arrayDao.getArrayDataBean()
            if (arrayDataBean.isNull() || arrayDataBean?.mLastTime.shouldUpdate()) {
                response(api.articleTop()) {
                    arrayDataBean = this
                    arrayDataBean?.mLastTime = System.currentTimeMillis()
                    arrayDao.insert(arrayDataBean)
                }
            }
            arrayDataBean
        }
    }.await()


    suspend fun articleList(page: Int) = withContext(Dispatchers.Default) {
        async {
            var tempPage = page //接口 page 索引 0 开始 ,返回数据 page 从 1 开始
            var objectBean = objectDao.getDataBean(++tempPage)

            if (NetworkUtils.isAvailable()) {
                if (objectBean.isNull() || objectBean?.mLastTime.shouldUpdate() || page >= (objectBean?.curPage ?: 0) || page == 0) {
                    response(api.articleList(tempPage)) {
                        objectBean = this.data
                        objectBean?.mLastTime = System.currentTimeMillis()
                        objectDao.insertDataBean(objectBean)
                    }
                }
            } else {
                if (objectBean.isNull()) {
                    netError()
                }
            }
            objectBean
        }
    }.await()

}