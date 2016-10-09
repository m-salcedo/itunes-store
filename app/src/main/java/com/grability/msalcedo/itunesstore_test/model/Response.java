
package com.grability.msalcedo.itunesstore_test.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Mariangela Salcedo (msalcedo047@gmail.com) on 08/10/16.
 * Copyright (c) 2016 m-salcedo. All rights reserved.
 */
public class Response {

    @SerializedName("feed")
    @Expose
    private Feed feed;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Response() {
    }

    /**
     * 
     * @param feed
     */
    public Response(Feed feed) {
        this.feed = feed;
    }

    /**
     * 
     * @return
     *     The feed
     */
    public Feed getFeed() {
        return feed;
    }

    /**
     * 
     * @param feed
     *     The feed
     */
    public void setFeed(Feed feed) {
        this.feed = feed;
    }

}
