package com.grability.msalcedo.itunesstore_test.presenter;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.grability.msalcedo.itunesstore_test.backend.request.Http;
import com.grability.msalcedo.itunesstore_test.backend.response.IHttpListener;
import com.grability.msalcedo.itunesstore_test.model.Entry;
import com.grability.msalcedo.itunesstore_test.model.Genre;
import com.grability.msalcedo.itunesstore_test.util.Util;
import com.grability.msalcedo.itunesstore_test.util.constant.Key;
import com.grability.msalcedo.itunesstore_test.view.activity.HelperActivity;
import com.grability.msalcedo.itunesstore_test.view.fragment.DetailFragment;
import com.grability.msalcedo.itunesstore_test.view.helper.adapter.CategoryAdapter;
import com.grability.msalcedo.itunesstore_test.view.helper.adapter.ItemAdapter;
import com.grability.msalcedo.itunesstore_test.view.iview.IItemListView;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;

import java.util.List;

import retrofit2.Call;

/**
 * Created by Mariangela Salcedo (msalcedo047@gmail.com) on 08/10/16.
 * Copyright (c) 2016 m-salcedo. All rights reserved.
 */
public class ItemListPresenter implements
        IHttpListener.Applications,
        SlidingUpPanelLayout.PanelSlideListener,
        CategoryAdapter.OnItemClickListener,
        ItemAdapter.OnItemClickListener {

    // ---------------------------------------------------------------------------------------------
    // ---------------------------------------------------------------------------------------------
    // Constants
    // ---------------------------------------------------------------------------------------------
    // ---------------------------------------------------------------------------------------------

    private static final String TAG = ItemListPresenter.class.getSimpleName();

    // ---------------------------------------------------------------------------------------------
    // ---------------------------------------------------------------------------------------------
    // Fields
    // ---------------------------------------------------------------------------------------------
    // ---------------------------------------------------------------------------------------------

    private IItemListView mView;
    private Call mCall;
    private int mCategory;
    private boolean mTwoPane;

    // ---------------------------------------------------------------------------------------------
    // ---------------------------------------------------------------------------------------------
    // Methods
    // ---------------------------------------------------------------------------------------------
    // ---------------------------------------------------------------------------------------------

    public ItemListPresenter(IItemListView view, boolean twoPane) {
        mView = view;
        mCategory = 0;
        mTwoPane = twoPane;
    }

    public void onDestroy() {
        if (mCall != null) {
            mCall.cancel();
        }
    }

    public void init() {
        mView.hideEmptyView();
        mView.showProgress();
        mView.setCategories(Genre.getCategories());
        mCall = Http.request(mView.getContext()).getFreeApplications(this);
    }

    @Override
    public void onAppSuccess(List<Entry> result) {
        Log.d(TAG, "onAppSuccess: " + result.size());
        if (result.isEmpty()) {
            onAppFailed();
        } else {
            mView.setItems(result, mCategory);
            mView.hideProgress();
        }
    }

    @Override
    public void onAppFailed() {
        Log.e(TAG, "onAppFailed");
        mView.hideProgress();
        mView.showEmptyView();
    }

    @Override
    public void onError(String message) {
        Log.e(TAG, "onError " + message);
        onAppFailed();
    }

    @Override
    public void onPanelSlide(View panel, float slideOffset) {
    }

    @Override
    public void onPanelStateChanged(View panel, SlidingUpPanelLayout.PanelState previousState,
                                    SlidingUpPanelLayout.PanelState newState) {

    }

    public void checkInternet() {
        if (Util.isNetworkAvailable()) {
            mView.hideOfflineMode();
        } else {
            mView.showOfflineMode();
        }
    }

    /***********************************************************************************************
     * Onclick Categories
     *
     * @param view
     * @param category
     */
    @Override
    public void onClickItem(View view, Genre category) {
        mView.hideEmptyView();
        mView.clearList();
        mView.showProgress();
        mView.hideCategories();
        mView.setTitleButton(category.getTitle());
        Http.request(mView.getContext()).getFreeApplications(category.getCode(), this);
        mCategory = category.getCode();

        checkInternet();

        if (mTwoPane) {
            mView.clearFragment();
        }
    }

    /***********************************************************************************************
     * Onclick Items
     *
     * @param view
     * @param item
     */

    @Override
    public void onClick(View view, Entry item) {
        if (mTwoPane) {
            checkInternet();
            Bundle arguments = new Bundle();
            arguments.putString(Key.ENTRY, Util.toString(item));
            DetailFragment fragment = new DetailFragment();
            fragment.setArguments(arguments);
            mView.mountFragment(fragment);
        } else {
            Intent intent = new Intent(mView.getContext(), HelperActivity.class);
            intent.putExtra(Key.ENTRY, Util.toString(item));
            mView.startActivity(intent);
        }
    }
}
