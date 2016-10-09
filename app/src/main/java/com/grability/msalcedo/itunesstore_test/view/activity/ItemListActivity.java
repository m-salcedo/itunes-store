package com.grability.msalcedo.itunesstore_test.view.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.grability.msalcedo.itunesstore_test.R;
import com.grability.msalcedo.itunesstore_test.model.Entry;
import com.grability.msalcedo.itunesstore_test.model.Genre;
import com.grability.msalcedo.itunesstore_test.presenter.ItemListPresenter;
import com.grability.msalcedo.itunesstore_test.view.helper.adapter.CategoryAdapter;
import com.grability.msalcedo.itunesstore_test.view.helper.adapter.ItemAdapter;
import com.grability.msalcedo.itunesstore_test.view.iview.IItemListView;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;


/**
 * Created by Mariangela Salcedo (msalcedo047@gmail.com) on 08/10/16.
 * Copyright (c) 2016 m-salcedo. All rights reserved.
 */
public class ItemListActivity extends AppCompatActivity implements IItemListView {

    // ---------------------------------------------------------------------------------------------
    // ---------------------------------------------------------------------------------------------
    // Constants
    // ---------------------------------------------------------------------------------------------
    // ---------------------------------------------------------------------------------------------

    private static final String TAG = ItemListActivity.class.getSimpleName();

    // ---------------------------------------------------------------------------------------------
    // ---------------------------------------------------------------------------------------------
    // Fields
    // ---------------------------------------------------------------------------------------------
    // ---------------------------------------------------------------------------------------------

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.recyclerItem)
    RecyclerView recyclerItem;
    @Bind(R.id.recyclerCategory)
    RecyclerView recyclerCategory;
    @Bind(R.id.slidingLayout)
    SlidingUpPanelLayout upPanelLayout;
    @Bind(R.id.button)
    Button button;
    @Bind(R.id.progress)
    View progress;
    @Bind(R.id.lyOffline)
    View lyOffline;
    @Bind(R.id.lyEmptyItems)
    View lyEmptyItems;

    private ItemAdapter mAdapter;
    private ItemListPresenter mPresenter;

    // ---------------------------------------------------------------------------------------------
    // ---------------------------------------------------------------------------------------------
    // Methods
    // ---------------------------------------------------------------------------------------------
    // ---------------------------------------------------------------------------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getResources().getBoolean(R.bool.tablet)) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        } else {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }

        overridePendingTransition(R.anim.fade_in, R.anim.no_anim);

        setContentView(R.layout.activity_list);

        ButterKnife.bind(this);

        mPresenter = new ItemListPresenter(this, (findViewById(R.id.container) != null));

        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

        initRecycler();

        upPanelLayout.addPanelSlideListener(mPresenter);

        mPresenter.init();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.checkInternet();
    }


    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_about:
                goToAbout();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void goToAbout() {
        startActivity(new Intent(getContext(), AboutActivity.class));
    }

    private void initRecycler() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerItem.setLayoutManager(linearLayoutManager);
        mAdapter = new ItemAdapter(new ArrayList<Entry>(), mPresenter, getContext());
        recyclerItem.setAdapter(mAdapter);
    }

    @OnClick(R.id.button)
    public void showCategories(View view) {
        upPanelLayout.setPanelState(SlidingUpPanelLayout.PanelState.EXPANDED);
    }

    @OnClick(R.id.ivDropDown)
    public void hideCategories() {
        upPanelLayout.setPanelState(SlidingUpPanelLayout.PanelState.HIDDEN);
    }

    @Override
    public void setTitleButton(String title) {
        button.setText(title);
    }

    @Override
    public void showProgress() {
        progress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progress.setVisibility(View.GONE);
    }

    @Override
    public void showEmptyView() {
        lyEmptyItems.setVisibility(View.VISIBLE);
    }

    @Override
    public void setItems(List<Entry> result, int category) {
        mAdapter.addItems(result, category);
    }

    @Override
    public void hideEmptyView() {
        lyEmptyItems.setVisibility(View.GONE);
    }

    @Override
    public void clearList() {
        mAdapter.clear();
    }

    @Override
    public void mountFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, fragment)
                .commit();
    }

    @Override
    public void hideOfflineMode() {
        lyOffline.setVisibility(View.GONE);
    }

    @Override
    public void showOfflineMode() {
        lyOffline.setVisibility(View.VISIBLE);
    }

    @Override
    public void clearFragment() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, new Fragment())
                .commit();
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    public void onBackPressed() {
        if (upPanelLayout.getPanelState() == SlidingUpPanelLayout.PanelState.EXPANDED) {
            upPanelLayout.setPanelState(SlidingUpPanelLayout.PanelState.HIDDEN);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public Activity getActivity() {
        return this;
    }

    public void setCategories(List<Genre> categories) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerCategory.setLayoutManager(linearLayoutManager);
        CategoryAdapter categoryAdapter = new CategoryAdapter(categories, getContext(), mPresenter);
        recyclerCategory.setAdapter(categoryAdapter);
    }
}
