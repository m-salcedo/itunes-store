package com.grability.msalcedo.itunesstore_test.util;

import android.app.Application;
import android.content.Context;

import com.grability.msalcedo.itunesstore_test.R;

import java.io.File;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by Mariangela Salcedo (msalcedo047@gmail.com) on 08/10/16.
 * Copyright (c) 2016 m-salcedo. All rights reserved.
 */
public class App extends Application {

    // ---------------------------------------------------------------------------------------------
    // ---------------------------------------------------------------------------------------------
    // Constants
    // ---------------------------------------------------------------------------------------------
    // ---------------------------------------------------------------------------------------------

    private static final String TAG = App.class.getSimpleName();
    private static Context context;
    private static File mCacheFile;
    private static boolean splash;

    // ---------------------------------------------------------------------------------------------
    // ---------------------------------------------------------------------------------------------
    // Methods
    // ---------------------------------------------------------------------------------------------
    // ---------------------------------------------------------------------------------------------

    public static Context getAppContext() {
        return App.context;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        mCacheFile = new File(getCacheDir(), "responses");

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Poppins-Regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );

        context = getApplicationContext();
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }

    public static File getCacheFile() {
        return mCacheFile;
    }

    public static boolean isSplash() {
        return splash;
    }

    public static void setSplash(boolean splash) {
        App.splash = splash;
    }
}
