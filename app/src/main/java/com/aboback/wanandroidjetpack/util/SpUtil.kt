package com.aboback.wanandroidjetpack.util

import android.util.Base64
import java.io.*

/**
 * Created by jhb on 2019-08-09.
 */
object SpUtil {

    private const val KEY_USER_FIRST_ACTIVE = "key_user_first_active" // 第一次激活
    private const val KEY_COOKIE = "key_cookie"


    private var spObj: SpCommon? = null

    private var spCookie = SpCommon("sp_name_cookie_config")

    //=============用户相关========================
    private var spUser = SpCommon()

    fun saveCookies(cookies: Set<String>) {
        spCookie.put(KEY_COOKIE, cookies)
    }

    private var mCacheCookies = mutableSetOf<String>()
    fun getCookies(): MutableSet<String> {
        if (mCacheCookies.isEmpty()) {
            mCacheCookies = spCookie.getStringSet(KEY_COOKIE) ?: mutableSetOf()
        }
        return mCacheCookies
    }


    fun isCookieExist(): Boolean {
        return !getCookies().isNullOrEmpty()
    }

    fun clearCookie() {
        spCookie.clear()
    }
    //=============用户相关========================


    fun saveObj(any: Any, key: String, spName: String) {
        if (spObj == null) {
            spObj = SpCommon(spName)
        }
        val baos = ByteArrayOutputStream()
        val oos = ObjectOutputStream(baos)
        try {
            oos.writeObject(any)
            val byteArray = baos.toByteArray()
            val encodeString = Base64.encodeToString(byteArray, Base64.DEFAULT)
            spObj?.put(key, encodeString)
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            oos.close()
            baos.close()
        }
    }

    fun getObj(key: String): Any? {
        val objStr = spObj?.getString(key, "") ?: ""
        val decodeByte = Base64.decode(objStr, Base64.DEFAULT)

        var any: Any? = null
        var bais: ByteArrayInputStream? = null
        var ois: ObjectInputStream? = null
        try {
            bais = ByteArrayInputStream(decodeByte)
            ois = ObjectInputStream(bais)
            any = ois.readObject()
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            bais?.close()
            ois?.close()
        }
        return any
    }

}