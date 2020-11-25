package com.aboback.wanandroidjetpack.db.dao

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
interface ObjectDataDao {

    @Query("SELECT * FROM ObjectDataBean_DataBean WHERE curPage = :page")
    fun getDataBean(page: Int): ObjectDataBean.DataBean?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDataBean(vararg listBean: ObjectDataBean.DataBean?)


}