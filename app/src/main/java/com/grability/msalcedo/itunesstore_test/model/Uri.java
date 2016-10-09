
package com.grability.msalcedo.itunesstore_test.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Mariangela Salcedo (msalcedo047@gmail.com) on 08/10/16.
 * Copyright (c) 2016 m-salcedo. All rights reserved.
 */
public class Uri {

    @SerializedName("label")
    @Expose
    private String label;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Uri() {
    }

    /**
     * 
     * @param label
     */
    public Uri(String label) {
        this.label = label;
    }

    /**
     * 
     * @return
     *     The label
     */
    public String getLabel() {
        return label;
    }

    /**
     * 
     * @param label
     *     The label
     */
    public void setLabel(String label) {
        this.label = label;
    }

}
