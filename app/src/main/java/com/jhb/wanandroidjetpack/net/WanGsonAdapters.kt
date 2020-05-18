package com.jhb.wanandroidjetpack.net

import com.google.gson.Gson
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type
import java.util.*


/**
 * @author jhb
 * @date 2020/5/18
 */

class IntDefaultAdapter : JsonDeserializer<Int> {
    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): Int? {
        if (json?.asString.equals("")) {
            return null
        }
        return try {
            json?.asInt
        } catch (e: Exception) {
            null
        }
    }
}


class ArraySecurityAdapter : JsonDeserializer<List<*>> {
    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): List<*> {
        if (json?.isJsonArray == true) {
            return Gson().fromJson(json, typeOfT)
        } else {
            return Collections.EMPTY_LIST
        }
    }
}

class ObjectSecurityAdapter : JsonDeserializer<Any> {
    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): Any {
        if (json?.isJsonObject == true) {
            return Gson().fromJson(json.asJsonObject, typeOfT)
        } else {
            return Any()
        }
    }
}



