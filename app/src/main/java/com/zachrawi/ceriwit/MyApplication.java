package com.zachrawi.ceriwit;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseInstallation;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("I7pPikXDup2kkY3AaYMhKAbgLjTXhXCcVoaKvGwk")
                .clientKey("1j3tGBbZABH4AV60bhVwSERksngouW8JE2Unu7fo")
                .server("https://parseapi.back4app.com/")
                .build());

        ParseInstallation.getCurrentInstallation().saveInBackground();
    }
}
