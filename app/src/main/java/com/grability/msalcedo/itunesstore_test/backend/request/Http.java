package com.grability.msalcedo.itunesstore_test.backend.request;

import android.content.Context;

import com.grability.msalcedo.itunesstore_test.backend.manager.RequestManager;
import com.grability.msalcedo.itunesstore_test.backend.response.IHttpListener;
import com.grability.msalcedo.itunesstore_test.model.Response;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by Mariangela Salcedo (msalcedo047@gmail.com) on 08/10/16.
 * Copyright (c) 2016 m-salcedo. All rights reserved.
 */
public class Http extends RequestManager {

    // ---------------------------------------------------------------------------------------------
    // ---------------------------------------------------------------------------------------------
    // Constants
    // ---------------------------------------------------------------------------------------------
    // ---------------------------------------------------------------------------------------------

    private static final String TAG = Http.class.getSimpleName();

    // ---------------------------------------------------------------------------------------------
    // ---------------------------------------------------------------------------------------------
    // Fields
    // ---------------------------------------------------------------------------------------------
    // ---------------------------------------------------------------------------------------------

    private Context mContext;

    // ---------------------------------------------------------------------------------------------
    // ---------------------------------------------------------------------------------------------
    // Methods
    // ---------------------------------------------------------------------------------------------
    // ---------------------------------------------------------------------------------------------

    private Http(Context context) {
        mContext = context;
    }

    public static Http request(Context context) {
        return new Http(context);
    }

    private static IHttp api(Context context) {
        return retrofit(context).create(IHttp.class);
    }

    public Call<Response> getFreeApplications(final IHttpListener.Applications listener) {
        return getFreeApplications(0, listener);
    }

    public Call<Response> getFreeApplications(int category, final IHttpListener.Applications listener) {
        Call<Response> call = api(mContext).getFreeApplications(category);
        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                if (call.isCanceled()) return;
                if (response.isSuccessful()) {
                    listener.onAppSuccess(response.body().getFeed().getEntry());
                } else {
                    listener.onAppFailed();
                }
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                if (call.isCanceled()) return;
                listener.onError(t != null ? t.getMessage() : "");
            }
        });
        return call;
    }
}
