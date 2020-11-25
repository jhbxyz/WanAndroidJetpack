package com.aboback.wanandroidjetpack.db

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.aboback.base.BaseApp
import com.aboback.wanandroidjetpack.bean.*
import com.aboback.wanandroidjetpack.db.dao.*

/**
 * @author jhb
 * @date 2020/6/4
 */
@Database(entities = [
    UserLoginBean.Data::class,
    CoinUserInfoBean.Data::class,
    ObjectDataBean.DataBean::class,
    ArrayDataBean::class,
    BannerDataBean::class
], version = 1)
abstract class WanDatabase : RoomDatabase() {

    abstract val userLoginDao: UserLoginDao

    abstract val coinUserInfoDao: CoinUserInfoDao

    abstract val objectDataDao: ObjectDataDao

    abstract val arrayDataDao: ArrayDataDao

    abstract val bannerDataDao: BannerDataDao


    companion object {

        @Volatile
        private var instance: WanDatabase? = null

        fun getInstance() = instance ?: synchronized(WanDatabase::class.java) {
            instance ?: buildDatabase().also { instance = it }
        }

        private fun buildDatabase(): WanDatabase = Room.databaseBuilder(BaseApp.instance, WanDatabase::class.java, "WanDatabase.db")
            .allowMainThreadQueries()
            .build()

    }


}