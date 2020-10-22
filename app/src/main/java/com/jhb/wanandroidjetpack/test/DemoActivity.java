package com.jhb.wanandroidjetpack.test;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @author jhb
 * @date 2020/6/10
 */
public class DemoActivity extends AppCompatActivity {

    private Handler mHanler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);


        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new Thread(() -> {
            Message message = Message.obtain();
            message.obj = "Hanler.sendMessage方法";
            mHanler.sendMessage(message);
        }).start();
    }
}
