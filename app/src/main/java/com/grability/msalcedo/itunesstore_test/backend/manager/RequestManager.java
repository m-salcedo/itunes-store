package com.grability.msalcedo.itunesstore_test.backend.manager;

import android.annotation.SuppressLint;
import android.content.Context;

import com.grability.msalcedo.itunesstore_test.R;
import com.grability.msalcedo.itunesstore_test.util.App;
import com.grability.msalcedo.itunesstore_test.util.Util;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Mariangela Salcedo (msalcedo047@gmail.com) on 08/10/16.
 * Copyright (c) 2016 m-salcedo. All rights reserved.
 */
public class RequestManager {

    // ---------------------------------------------------------------------------------------------
    // ---------------------------------------------------------------------------------------------
    // Constants
    // ---------------------------------------------------------------------------------------------
    // ---------------------------------------------------------------------------------------------

    private static final String TAG = RequestManager.class.getSimpleName();
    private static Retrofit mRetrofit;


    // ---------------------------------------------------------------------------------------------
    // ---------------------------------------------------------------------------------------------
    // Methods
    // ---------------------------------------------------------------------------------------------
    // ---------------------------------------------------------------------------------------------

    protected static Retrofit retrofit(final Context context) {
        if (mRetrofit == null || App.isSplash()) {

            Cache cache = new Cache(App.getCacheFile(), 10 * 1024 * 1024);

            MyInterceptor interceptor = new MyInterceptor();

            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .readTimeout(15, TimeUnit.SECONDS)
                    .connectTimeout(15, TimeUnit.SECONDS)
                    .cache(cache)
                    .build();

            mRetrofit = new Retrofit.Builder()
                    .baseUrl(context.getString(R.string.url_api_current))
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();
        }

        return mRetrofit;
    }

    private static class MyInterceptor implements Interceptor {

        @SuppressLint("DefaultLocale")
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request original = chain.request();
            Request request;
            String max;

            if (!Util.isNetworkAvailable()) {
                max = String.format("max-stale=%d", (60 * 60 * 24 * 28)); // tolerate 4-weeks stale
            } else if (App.isSplash()) {
                max = String.format("max-age=%d", 1); // request new items
            } else {
                max = String.format("max-age=%d", 60 * 10); // read from cache for 10 minutes
            }

            request = original.newBuilder()
                    .header("Content-Type", "application/json")
                    .removeHeader("Pragma")
                    .header("Cache-Control", max)
                    .build();

            Response response = chain.proceed(request);
            response.cacheResponse();
            return response;
        }
    }
}

