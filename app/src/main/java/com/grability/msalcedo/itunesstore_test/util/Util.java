package com.grability.msalcedo.itunesstore_test.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.google.gson.Gson;
import com.grability.msalcedo.itunesstore_test.model.Entry;

/**
 * Created by Mariangela Salcedo (msalcedo047@gmail.com) on 08/10/16.
 * Copyright (c) 2016 m-salcedo. All rights reserved.
 */
public class Util {

    // ---------------------------------------------------------------------------------------------
    // ---------------------------------------------------------------------------------------------
    // Constants
    // ---------------------------------------------------------------------------------------------
    // ---------------------------------------------------------------------------------------------

    private static final String TAG = Util.class.getSimpleName();

    // ---------------------------------------------------------------------------------------------
    // ---------------------------------------------------------------------------------------------
    // Methods
    // ---------------------------------------------------------------------------------------------
    // ---------------------------------------------------------------------------------------------

    public static boolean isNetworkAvailable() {
        ConnectivityManager cm =
                (ConnectivityManager) App
                        .getAppContext()
                        .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    public static String toString(Object object) {
        Gson gson = new Gson();
        String json = gson.toJson(object);
        return json;
    }
}
