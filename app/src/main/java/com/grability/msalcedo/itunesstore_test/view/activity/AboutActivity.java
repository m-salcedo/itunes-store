package com.grability.msalcedo.itunesstore_test.view.activity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.grability.msalcedo.itunesstore_test.R;
import com.grability.msalcedo.itunesstore_test.util.constant.URL;

import butterknife.ButterKnife;
import butterknife.OnClick;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by Mariangela Salcedo (msalcedo047@gmail.com) on 09/10/16.
 * Copyright (c) 2016 m-salcedo. All rights reserved.
 */
public class AboutActivity extends AppCompatActivity {

    // ---------------------------------------------------------------------------------------------
    // ---------------------------------------------------------------------------------------------
    // Constants
    // ---------------------------------------------------------------------------------------------
    // ---------------------------------------------------------------------------------------------

    private static final String TAG = AboutActivity.class.getSimpleName();

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
        overridePendingTransition(R.anim.slide_enter_bottom, R.anim.no_anim);
        setContentView(R.layout.activity_about);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.tvTest)
    public void goToDocument() {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(URL.DOCUMENT));
        startActivity(browserIntent);
    }


    @OnClick(R.id.tvRight)
    public void goToGithub() {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(URL.GITHUB));
        startActivity(browserIntent);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.no_anim, R.anim.slide_exit_bottom);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
