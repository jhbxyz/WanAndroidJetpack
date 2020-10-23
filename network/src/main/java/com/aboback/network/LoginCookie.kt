package com.aboback.network

import okhttp3.Cookie
import okhttp3.CookieJar
import okhttp3.HttpUrl

/**
 * Created by jhb on 2020-01-16.
 */
class LoginCookie : CookieJar {

    override fun loadForRequest(url: HttpUrl): List<Cookie> {

        return emptyList()
    }

    override fun saveFromResponse(url: HttpUrl, cookies: List<Cookie>) {

    }
}