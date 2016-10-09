package com.grability.msalcedo.itunesstore_test.view.activity;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.grability.msalcedo.itunesstore_test.R;
import com.grability.msalcedo.itunesstore_test.presenter.SplashPresenter;
import com.grability.msalcedo.itunesstore_test.util.App;
import com.grability.msalcedo.itunesstore_test.view.iview.ISplashView;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by Mariangela Salcedo (msalcedo047@gmail.com) on 08/10/16.
 * Copyright (c) 2016 m-salcedo. All rights reserved.
 */
public class SplashActivity extends AppCompatActivity implements ISplashView {

    // ---------------------------------------------------------------------------------------------
    // ---------------------------------------------------------------------------------------------
    // Constants
    // ---------------------------------------------------------------------------------------------
    // ---------------------------------------------------------------------------------------------

    private static final String TAG = SplashActivity.class.getSimpleName();

    // ---------------------------------------------------------------------------------------------
    // ---------------------------------------------------------------------------------------------
    // Fields
    // ---------------------------------------------------------------------------------------------
    // ---------------------------------------------------------------------------------------------

    private SplashPresenter mPresenter;

    // ---------------------------------------------------------------------------------------------
    // ---------------------------------------------------------------------------------------------
    // Methods
    // ---------------------------------------------------------------------------------------------
    // ---------------------------------------------------------------------------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getResources().getBoolean(R.bool.tablet)) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        } else {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_splash);

        App.setSplash(true);

        mPresenter = new SplashPresenter(this);
        mPresenter.init();
    }

    @Override
    protected void onDestroy() {
        mPresenter.onDestroy();
        App.setSplash(false);
        super.onDestroy();
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public Activity getActivity() {
        return this;
    }

    @Override
    public void goToList() {
        startActivity(new Intent(getContext(), ItemListActivity.class));
        finish();
    }
}
