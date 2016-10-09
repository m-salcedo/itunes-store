package com.grability.msalcedo.itunesstore_test.view.iview;

import android.content.Intent;
import android.support.v4.app.Fragment;

import com.grability.msalcedo.itunesstore_test.model.Entry;
import com.grability.msalcedo.itunesstore_test.model.Genre;

import java.util.List;

/**
 * Created by Mariangela Salcedo (msalcedo047@gmail.com) on 08/10/16.
 * Copyright (c) 2016 m-salcedo. All rights reserved.
 */
public interface IItemListView extends IBaseView {

    void setCategories(List<Genre> categories);

    void hideCategories();

    void setTitleButton(String title);

    void showProgress();

    void hideProgress();

    void showEmptyView();

    void setItems(List<Entry> result, int category);

    void hideEmptyView();

    void clearList();

    void mountFragment(Fragment fragment);

    void startActivity(Intent intent);

    void hideOfflineMode();

    void showOfflineMode();

    void clearFragment();
}
