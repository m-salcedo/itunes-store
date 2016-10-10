# itunes-store
- [Grability android application test](https://drive.google.com/file/d/0B0EF3LWHV1mmZDNBY185ZFNwM2JlcWFYamxPM244TC1CX2pN/view?usp=sharing)
- [Apk download link](https://drive.google.com/open?id=0B0EF3LWHV1mmblhuYW14cXBZbW8)

Contents table
-----------------

- [Technical specifications](#technical-specifications)
- [Structure](#structure)
- [Libraries](#libraries)


Technical specifications
-------------

- Android 4+
- [Services](https://itunes.apple.com/us/rss/topfreeapplications/limit=20/json)


Structure
---------------

- [MVP (Model - View - Presenter)] (https://github.com/konmik/konmik.github.io/wiki/Introduction-to-Model-View-Presenter-on-Android)


Libraries
---------------

```bash
# Support libraries
    compile 'com.android.support:appcompat-v7:23.4.0'
    compile 'com.android.support:support-v4:23.4.0'
    compile 'com.android.support:recyclerview-v7:23.4.0'
    compile 'com.android.support:cardview-v7:23.4.0'
    compile 'com.android.support:design:23.4.0'

# Custom font
    compile 'uk.co.chrisjenx:calligraphy:2.2.0'

# HTTP client
    compile 'com.squareup.retrofit2:converter-gson:2.1.0'
    compile 'com.squareup.retrofit2:retrofit:2.1.0'
    compile 'com.squareup.okhttp3:okhttp:3.4.1'

# Loading image
    compile 'com.github.bumptech.glide:glide:3.7.0'

# Other
    compile 'com.jakewharton:butterknife:7.0.1'
    compile 'com.sothree.slidinguppanel:library:3.3.1'
    compile 'com.wang.avi:library:2.1.3'
