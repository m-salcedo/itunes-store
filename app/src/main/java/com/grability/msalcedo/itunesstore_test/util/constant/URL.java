package com.grability.msalcedo.itunesstore_test.util.constant;

/**
 * Created by Mariangela Salcedo (msalcedo047@gmail.com) on 08/10/16.
 * Copyright (c) 2016 m-salcedo. All rights reserved.
 */
public class URL {

    // ---------------------------------------------------------------------------------------------
    // ---------------------------------------------------------------------------------------------
    // Constants
    // ---------------------------------------------------------------------------------------------
    // ---------------------------------------------------------------------------------------------

    private static final int LIMIT = 20;

    private static final String RSS = "/us/rss";
    public static final String DOCUMENT = "https://drive.google.com/file/d/0B0EF3LWHV1mmZDNBY185ZFNwM2JlcWFYamxPM244TC1CX2pN/view?usp=sharing";
    public static final String GITHUB = "https://github.com/m-salcedo";

    public static class APPLICATIONS {
        public static final String TOP_FREE = RSS + "/topfreeapplications/limit=" + LIMIT + "/genre={" + Key.CATEGORY + "}/json";
    }
}
