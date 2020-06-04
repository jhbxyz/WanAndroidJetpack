package com.jhb.wanandroidjetpack.db

import com.jhb.wanandroidjetpack.bean.WendaListBean
import com.jhb.wanandroidjetpack.util.WanExecutors

/**
 * @author jhb
 * @date 2020/6/4
 */
object WenDaListManger {

    private val mDB by lazy { WanDatabase.getInstance() }

    private val mDao by lazy { mDB.wenDaListDao }

    fun insertDataBean(datas: WendaListBean.DataBean) {
        WanExecutors.mDiskIO.execute {
            mDao.insertDataBean(datas)

        }

    }


    fun getDataBean(action: (WendaListBean.DataBean?) -> Unit) {
        WanExecutors.mDiskIO.execute {
            val dataBean = mDao.getDataBean()
            action.invoke(dataBean)

        }
    }

}