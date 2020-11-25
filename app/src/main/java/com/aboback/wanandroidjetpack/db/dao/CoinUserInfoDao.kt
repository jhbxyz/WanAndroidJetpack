package com.aboback.wanandroidjetpack.db.dao

import androidx.room.*
import com.aboback.wanandroidjetpack.bean.CoinUserInfoBean

/**
 * @author jhb
 * @date 2020/11/23
 */
@Dao
interface CoinUserInfoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(data: CoinUserInfoBean.Data?)

    @Query("SELECT * FROM CoinUserInfoData WHERE userId = :userId")
    fun getCoinUserInfo(userId: Int?): CoinUserInfoBean.Data?

    @Update
    fun updateCoinInfo(data: CoinUserInfoBean.Data?)


}