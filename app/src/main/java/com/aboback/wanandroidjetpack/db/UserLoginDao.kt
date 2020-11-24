package com.aboback.wanandroidjetpack.db

import androidx.room.*
import com.aboback.wanandroidjetpack.bean.CoinUserInfoBean
import com.aboback.wanandroidjetpack.bean.UserLoginBean

/**
 * @author jhb
 * @date 2020/11/24
 */
@Dao
interface UserLoginDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(data: UserLoginBean.Data?)

    @Query("SELECT * FROM UserLoginData")
    fun getUserInfo(): UserLoginBean.Data?

    @Update(onConflict = OnConflictStrategy.REPLACE, entity = UserLoginBean.Data::class)
    fun onUserLogout(data: UserLoginBean.Data)


}