package com.wilddog.wilddogsyncdemo;

import android.app.Application;

import com.wilddog.wilddogcore.WilddogApp;
import com.wilddog.wilddogcore.WilddogOptions;

/**
 * Created by Administrator on 2016/9/21.
 */
public class WilddogSyncDemoApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //TODO change the url  to your app
        WilddogOptions wilddogOptions = new WilddogOptions.Builder().setSyncUrl("http://testjar.wilddogio.com").build();
        WilddogApp.initializeApp(this,wilddogOptions);
    }
}
