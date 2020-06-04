package com.jhb.wanandroidjetpack.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jhb.wanandroidjetpack.bean.WendaListBean

/**
 * @author jhb
 * @date 2020/6/4
 */
@Dao
interface WenDaListDao {

    @Query("SELECT * FROM WendaListBeanDataBean")
    fun getDataBean(): WendaListBean.DataBean?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDataBean(vararg listBean: WendaListBean.DataBean)


}