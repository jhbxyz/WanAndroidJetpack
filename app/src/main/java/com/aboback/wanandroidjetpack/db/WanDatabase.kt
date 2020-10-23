package com.aboback.wanandroidjetpack.db

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.aboback.base.BaseApp
import com.aboback.wanandroidjetpack.base.WanApp
import com.aboback.wanandroidjetpack.bean.WendaListBean

/**
 * @author jhb
 * @date 2020/6/4
 */
@Database(entities = [WendaListBean.DataBean::class], version = 1)
abstract class WanDatabase : RoomDatabase() {

    abstract val wenDaListDao: WenDaListDao


    companion object {

        @Volatile
        private var instance: WanDatabase? = null

        fun getInstance() = instance ?: synchronized(WanDatabase::class.java) {
            instance ?: buildDatabase().also { instance = it }

        }

        private fun buildDatabase(): WanDatabase = Room.databaseBuilder(BaseApp.instance, WanDatabase::class.java, "WanDatabase.db")
//            .allowMainThreadQueries()
            .build()

    }


}