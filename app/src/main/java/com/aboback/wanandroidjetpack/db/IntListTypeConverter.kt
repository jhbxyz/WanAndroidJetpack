package com.aboback.wanandroidjetpack.db

import androidx.room.TypeConverter
import com.aboback.wanandroidjetpack.bean.ItemDatasBean
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/**
 * @author jhb
 * @date 2020/11/24
 */
class IntListTypeConverter {

    @TypeConverter
    fun stringToList(s: String?): List<Int>? =
            Gson().fromJson(s, object : TypeToken<List<Int>>() {}.type)

    @TypeConverter
    fun listToString(list: List<Int>?): String? = Gson().toJson(list)

}






