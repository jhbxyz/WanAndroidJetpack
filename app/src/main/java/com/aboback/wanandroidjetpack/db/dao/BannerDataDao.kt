package com.aboback.wanandroidjetpack.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.aboback.wanandroidjetpack.bean.BannerDataBean

/**
 * @author jhb
 * @date 2020/6/4
 */
@Dao
interface BannerDataDao {

    @Query("SELECT * FROM BannerBean")
    fun getDataBean(): BannerDataBean?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg bean: BannerDataBean?)


}