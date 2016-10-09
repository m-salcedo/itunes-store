package com.grability.msalcedo.itunesstore_test.view.activity;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.grability.msalcedo.itunesstore_test.R;
import com.grability.msalcedo.itunesstore_test.util.Util;
import com.grability.msalcedo.itunesstore_test.util.constant.Key;
import com.grability.msalcedo.itunesstore_test.view.fragment.DetailFragment;

import butterknife.Bind;
import butterknife.ButterKnife;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;


/**
 * Created by Mariangela Salcedo (msalcedo047@gmail.com) on 08/10/16.
 * Copyright (c) 2016 m-salcedo. All rights reserved.
 */
public class HelperActivity extends AppCompatActivity {

    // ---------------------------------------------------------------------------------------------
    // ---------------------------------------------------------------------------------------------
    // Constants
    // ---------------------------------------------------------------------------------------------
    // ---------------------------------------------------------------------------------------------

    private static final String TAG = HelperActivity.class.getSimpleName();

    // ---------------------------------------------------------------------------------------------
    // ---------------------------------------------------------------------------------------------
    // Fields
    // ---------------------------------------------------------------------------------------------
    // ---------------------------------------------------------------------------------------------

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.lyOffline)
    View lyOffline;

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

        overridePendingTransition(R.anim.slide_enter_right, R.anim.no_anim);

        setContentView(R.layout.activity_helper);

        ButterKnife.bind(this);

        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_action_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        if (savedInstanceState == null) {
            Bundle arguments = new Bundle();
            arguments.putString(Key.ENTRY, getIntent().getStringExtra(Key.ENTRY));
            DetailFragment fragment = new DetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container, fragment)
                    .commit();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (Util.isNetworkAvailable()) {
            lyOffline.setVisibility(View.GONE);
        } else {
            lyOffline.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.no_anim, R.anim.slide_exit_right);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
