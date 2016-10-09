package com.grability.msalcedo.itunesstore_test.backend.response;

import com.grability.msalcedo.itunesstore_test.model.Entry;

import java.util.List;

/**
 * Created by Mariangela Salcedo (msalcedo047@gmail.com) on 08/10/16.
 * Copyright (c) 2016 m-salcedo. All rights reserved.
 */
public interface IHttpListener {
    interface Applications {
        void onAppSuccess(List<Entry> result);
        void onAppFailed();
        void onError(String message);
    }
}
