package com.grability.msalcedo.itunesstore_test.presenter;

import com.grability.msalcedo.itunesstore_test.R;
import com.grability.msalcedo.itunesstore_test.model.Entry;
import com.grability.msalcedo.itunesstore_test.view.activity.HelperActivity;
import com.grability.msalcedo.itunesstore_test.view.iview.IDetailView;

/**
 * Created by Mariangela Salcedo (msalcedo047@gmail.com) on 09/10/16.
 * Copyright (c) 2016 m-salcedo. All rights reserved.
 */
public class DetailPresenter {

    // ---------------------------------------------------------------------------------------------
    // ---------------------------------------------------------------------------------------------
    // Constants
    // ---------------------------------------------------------------------------------------------
    // ---------------------------------------------------------------------------------------------

    private static final String TAG = DetailPresenter.class.getSimpleName();
    private static final int MAX_LINES = 5;

    // ---------------------------------------------------------------------------------------------
    // ---------------------------------------------------------------------------------------------
    // Fields
    // ---------------------------------------------------------------------------------------------
    // ---------------------------------------------------------------------------------------------

    private IDetailView mView;
    private Entry mEntry;
    private boolean descriptionCollapsed;
    private int mLinesCountDescription;

    // ---------------------------------------------------------------------------------------------
    // ---------------------------------------------------------------------------------------------
    // Methods
    // ---------------------------------------------------------------------------------------------
    // ---------------------------------------------------------------------------------------------

    public DetailPresenter(IDetailView view, Entry item) {
        mView = view;
        mEntry = item;
    }

    public void init() {
        if (mEntry == null) return;

        mView.setIcon(mEntry.getImage().getLabel());

        mView.setTitle(mEntry.getTitle().getLabel());

        mView.setAuthor(mEntry.getImArtist().getLabel());

        mView.setCategory(mEntry.getCategory().getAttributes().getLabel());

        mView.setDescription(mEntry.getSummary().getLabel());

        mView.setType(mEntry.getImContentType().getAttributes().getLabel());

        mView.setPackage(mEntry.getId().getAttributes().getImBundleId());

        mView.setDate(mEntry.getImReleaseDate().getAttributes().getLabel());

        mView.setRight(mEntry.getRights().getLabel());

        mView.setLink(mEntry.getId().getLabel());
    }

    public void btnShowMore() {
        descriptionCollapsed = !descriptionCollapsed;

        if (descriptionCollapsed) {
            mView.setTextBtn(R.string.label_show_more);
            mView.setMaxLinesDescription(MAX_LINES);
        } else {
            mView.setTextBtn(R.string.label_show_less);
            mView.setMaxLinesDescription(mLinesCountDescription);
        }
    }

    public void descriptionLoaded(int lineCount) {
        mLinesCountDescription = lineCount;

        if (mLinesCountDescription > MAX_LINES) {
            descriptionCollapsed = true;
            mView.showBtnMore();
            mView.setMaxLinesDescription(MAX_LINES);
        } else {
            mView.hideBtnMore();
        }
    }
}
