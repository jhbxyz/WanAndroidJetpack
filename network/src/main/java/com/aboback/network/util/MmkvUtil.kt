package com.aboback.network.util

import com.tencent.mmkv.MMKV

/**
 * @author jhb
 * @date 2020/10/30
 */
object MmkvUtil {

    private const val KEY_COOKIE = "key_cookie"
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

}