package com.aboback.wanandroidjetpack.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.aboback.wanandroidjetpack.bean.ArrayDataBean
import com.aboback.wanandroidjetpack.bean.ObjectDataBean

/**
 * @author jhb
 * @date 2020/6/4
 */
@Dao
interface ArrayDataDao {

    @Query("SELECT * FROM ArrayDataBean")
    fun getArrayDataBean(): ArrayDataBean?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg bean: ArrayDataBean?)


}