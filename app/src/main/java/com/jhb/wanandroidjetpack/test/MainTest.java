package com.jhb.wanandroidjetpack.test;

import com.blankj.utilcode.util.EncryptUtils;

/**
 * Created by jhb on 2020-01-19.
 */
public class MainTest {


    public static void main(String[] args) {
        String string = EncryptUtils
                .encryptAES("123456".getBytes(), "qwertyuiop".getBytes(), "AES/ECB/PKCS5Padding", null).toString();


        System.out.println("string  = " + string);
    }
}
