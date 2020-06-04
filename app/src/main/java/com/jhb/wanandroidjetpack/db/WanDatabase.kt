package com.jhb.wanandroidjetpack.db

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.jhb.wanandroidjetpack.base.WanApp
import com.jhb.wanandroidjetpack.bean.WendaListBean

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

        private fun buildDatabase(): WanDatabase = Room.databaseBuilder(WanApp.instance, WanDatabase::class.java, "WanDatabase.db")
//            .allowMainThreadQueries()
            .build()

    }


}