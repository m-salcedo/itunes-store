package com.grability.msalcedo.itunesstore_test.backend.request;

import com.grability.msalcedo.itunesstore_test.model.Response;
import com.grability.msalcedo.itunesstore_test.util.constant.Key;
import com.grability.msalcedo.itunesstore_test.util.constant.URL;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Mariangela Salcedo (msalcedo047@gmail.com) on 08/10/16.
 * Copyright (c) 2016 m-salcedo. All rights reserved.
 */
public interface IHttp {
    @GET(URL.APPLICATIONS.TOP_FREE)
    Call<Response> getFreeApplications(
            @Path(Key.CATEGORY) int category
    );
}
