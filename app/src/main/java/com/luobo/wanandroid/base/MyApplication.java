package com.luobo.wanandroid.base;

import android.app.Application;
import android.content.Context;

public class MyApplication extends Application{
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        DemoThread demoThread = new DemoThread();

        Thread runnableThread = new Thread(new RunnableDemoThread());


        Thread noNameThread = new Thread(){
            @Override
            public void run() {

            }
        };
        demoThread.start();
        runnableThread.start();
    }

    public static Context getInstance() {
        return mContext;
    }
    class DemoThread extends Thread {
        @Override
        public void run(){
            System.out.println("this is a new thread");
        }
    }

    class RunnableDemoThread implements Runnable {
        public void run(){
            System.out.println("this is a new thread crate by interface");
        }
    }
}