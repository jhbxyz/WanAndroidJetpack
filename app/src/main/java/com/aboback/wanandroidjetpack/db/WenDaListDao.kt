package com.aboback.wanandroidjetpack.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.aboback.wanandroidjetpack.bean.ObjectDataBean

/**
 * @author jhb
 * @date 2020/6/4
 */
@Dao
interface WenDaListDao {

    @Query("SELECT * FROM WendaListBeanDataBean")
    fun getDataBean(): ObjectDataBean.DataBean?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDataBean(vararg listBean: ObjectDataBean.DataBean)


}