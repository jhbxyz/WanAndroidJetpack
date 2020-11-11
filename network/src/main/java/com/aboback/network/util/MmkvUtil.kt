package com.aboback.network.util

import com.tencent.mmkv.MMKV

/**
 * @author jhb
 * @date 2020/10/30
 */
object MmkvUtil {

    private const val KEY_COOKIE = "key_cookie"
    private const val USER_NIKE_NAME = "user_nike_name"
    private var kv = MMKV.defaultMMKV()

    fun saveCookie(set: Set<String>) {
        kv.encode(KEY_COOKIE, set)
    }

    fun getCookies() = kv.decodeStringSet(KEY_COOKIE)

    fun clearCookies() {
        kv.clearAll()
    }

    fun isLogin() = if (getCookies().isNullOrEmpty()) {
        false
    } else {
        !getCookies().filter { it.contains("token_pass") }.isNullOrEmpty()
    }

    fun saveNikeName(name: String) {
        kv.encode(USER_NIKE_NAME, name)
    }

    fun getNikeName() = kv.decodeString(USER_NIKE_NAME)

}