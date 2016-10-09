package com.grability.msalcedo.itunesstore_test.presenter;

import android.util.Log;

import com.grability.msalcedo.itunesstore_test.backend.request.Http;
import com.grability.msalcedo.itunesstore_test.backend.response.IHttpListener;
import com.grability.msalcedo.itunesstore_test.model.Entry;
import com.grability.msalcedo.itunesstore_test.view.iview.ISplashView;

import java.util.List;

import retrofit2.Call;

/**
 * Created by Mariangela Salcedo (msalcedo047@gmail.com) on 08/10/16.
 * Copyright (c) 2016 m-salcedo. All rights reserved.
 */
public class SplashPresenter implements IHttpListener.Applications {

    // ---------------------------------------------------------------------------------------------
    // ---------------------------------------------------------------------------------------------
    // Constants
    // ---------------------------------------------------------------------------------------------
    // ---------------------------------------------------------------------------------------------

    private static final String TAG = SplashPresenter.class.getSimpleName();

    // ---------------------------------------------------------------------------------------------
    // ---------------------------------------------------------------------------------------------
    // Fields
    // ---------------------------------------------------------------------------------------------
    // ---------------------------------------------------------------------------------------------

    private ISplashView mView;
    private Call mCall;

    // ---------------------------------------------------------------------------------------------
    // ---------------------------------------------------------------------------------------------
    // Methods
    // ---------------------------------------------------------------------------------------------
    // ---------------------------------------------------------------------------------------------

    public SplashPresenter(ISplashView view) {
        mView = view;
    }

    public void onDestroy() {
        if (mCall != null) {
            mCall.cancel();
        }
    }

    public void init() {
        mCall = Http.request(mView.getContext()).getFreeApplications(this);
    }

    @Override
    public void onAppSuccess(List<Entry> result) {
        Log.d(TAG, "onAppSuccess: " + result.size());
        mView.goToList();
    }

    @Override
    public void onAppFailed() {
        Log.e(TAG, "onAppFailed");
        mView.goToList();
    }

    @Override
    public void onError(String message) {
        Log.e(TAG, "onError");
        mView.goToList();
    }
}
