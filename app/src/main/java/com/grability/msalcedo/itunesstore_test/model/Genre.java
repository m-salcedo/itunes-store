package com.grability.msalcedo.itunesstore_test.model;

import android.util.Log;

import com.grability.msalcedo.itunesstore_test.R;
import com.grability.msalcedo.itunesstore_test.util.App;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mariangela Salcedo (msalcedo047@gmail.com) on 08/10/16.
 * Copyright (c) 2016 m-salcedo. All rights reserved.
 */
public class Genre {

    // ---------------------------------------------------------------------------------------------
    // ---------------------------------------------------------------------------------------------
    // Constants
    // ---------------------------------------------------------------------------------------------
    // ---------------------------------------------------------------------------------------------

    private static final String TAG = Genre.class.getSimpleName();

    // ---------------------------------------------------------------------------------------------
    // ---------------------------------------------------------------------------------------------
    // Fields
    // ---------------------------------------------------------------------------------------------
    // ---------------------------------------------------------------------------------------------

    private String mTitle;
    private int mCode;

    // ---------------------------------------------------------------------------------------------
    // ---------------------------------------------------------------------------------------------
    // Methods
    // ---------------------------------------------------------------------------------------------
    // ---------------------------------------------------------------------------------------------

    public Genre(String name, int id) {
        mTitle = name;
        mCode = id;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        this.mTitle = title;
    }

    public int getCode() {
        return mCode;
    }

    public void setCode(int code) {
        this.mCode = code;
    }

    public static List<Genre> getCategories() {
        List<Genre> list = new ArrayList<>();

        String[] names = App.getAppContext().getResources().getStringArray(R.array.categories_name);
        int[] ids = App.getAppContext().getResources().getIntArray(R.array.categories_id);

        for (int i = 0; i < names.length; i++) {
            list.add(new Genre(names[i], ids[i]));
        }

        Log.d(TAG, "getCategories: " + list.size());

        return list;
    }
}
