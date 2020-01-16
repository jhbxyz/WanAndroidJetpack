package com.jhb.wanandroidjetpack.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.jhb.wanandroidjetpack.base.WanApp;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * Created by jhb on 2020-01-16.
 */
public class CookieUtil implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Response originalResponse = chain.proceed(chain.request());

        if (!originalResponse.headers("Set-Cookie").isEmpty()) {
            HashSet<String> cookies = new HashSet<>();

            List<String> headers = originalResponse.headers("Set-Cookie");

            for (String header : originalResponse.headers("Set-Cookie")) {
                cookies.add(header);
            }

            SharedPreferences.Editor config = WanApp.instance.getSharedPreferences("config", Context.MODE_PRIVATE)
                    .edit();
            config.putStringSet("cookie", cookies);
            config.commit();
        }

        return originalResponse;
    }
}
