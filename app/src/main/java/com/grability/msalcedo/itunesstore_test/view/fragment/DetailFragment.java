package com.grability.msalcedo.itunesstore_test.view.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.grability.msalcedo.itunesstore_test.R;
import com.grability.msalcedo.itunesstore_test.model.Entry;
import com.grability.msalcedo.itunesstore_test.presenter.DetailPresenter;
import com.grability.msalcedo.itunesstore_test.util.constant.Key;
import com.grability.msalcedo.itunesstore_test.view.iview.IDetailView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Mariangela Salcedo (msalcedo047@gmail.com) on 08/10/16.
 * Copyright (c) 2016 m-salcedo. All rights reserved.
 */
public class DetailFragment extends Fragment implements IDetailView {

    // ---------------------------------------------------------------------------------------------
    // ---------------------------------------------------------------------------------------------
    // Constants
    // ---------------------------------------------------------------------------------------------
    // ---------------------------------------------------------------------------------------------

    private static final String TAG = DetailFragment.class.getSimpleName();

    // ---------------------------------------------------------------------------------------------
    // ---------------------------------------------------------------------------------------------
    // Fields
    // ---------------------------------------------------------------------------------------------
    // ---------------------------------------------------------------------------------------------

    @Bind(R.id.ivIcon)
    ImageView ivIcon;
    @Bind(R.id.tvTitle)
    TextView tvTitle;
    @Bind(R.id.tvAuthor)
    TextView tvAuthor;
    @Bind(R.id.tvCategory)
    TextView tvCategory;
    @Bind(R.id.tvDescription)
    TextView tvDescription;
    @Bind(R.id.btnShowMore)
    Button btnShowMore;
    @Bind(R.id.tvType)
    TextView tvType;
    @Bind(R.id.tvPackage)
    TextView tvPackage;
    @Bind(R.id.tvDate)
    TextView tvDate;
    @Bind(R.id.tvRight)
    TextView tvRight;
    @Bind(R.id.tvLink)
    TextView tvLink;

    private DetailPresenter mPresenter;

    // ---------------------------------------------------------------------------------------------
    // ---------------------------------------------------------------------------------------------
    // Methods
    // ---------------------------------------------------------------------------------------------
    // ---------------------------------------------------------------------------------------------

    public DetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Entry entry = (getArguments() != null && getArguments().containsKey(Key.ENTRY)) ?
                Entry.valueOf(getArguments().getString(Key.ENTRY)) :
                null;

        mPresenter = new DetailPresenter(this, entry);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_detail, container, false);

        ButterKnife.bind(this, rootView);

        mPresenter.init();

        return rootView;
    }

    @OnClick(R.id.btnShowMore)
    public void btnShowMore() {
        mPresenter.btnShowMore();
    }

    @Override
    public void setIcon(String url) {
        Glide.with(getContext())
                .load(url)
                .into(ivIcon);
    }

    @Override
    public void setTitle(String title) {
        tvTitle.setText(title);
    }

    @Override
    public void setAuthor(String author) {
        tvAuthor.setText(author);
    }

    @Override
    public void setCategory(String category) {
        tvCategory.setText(category);
    }

    @Override
    public void setDescription(String description) {
        tvDescription.setVisibility(View.INVISIBLE);
        tvDescription.setText(description);
        tvDescription.post(new Runnable() {
            @Override
            public void run() {
                mPresenter.descriptionLoaded(tvDescription.getLineCount());
                tvDescription.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public void setType(String type) {
        tvType.setText(type);
    }

    @Override
    public void setPackage(String string) {
        tvPackage.setText(string);
    }

    @Override
    public void setDate(String date) {
        tvDate.setText(date);
    }

    @Override
    public void setRight(String right) {
        tvRight.setText(right);
    }

    @Override
    public void showBtnMore() {
        btnShowMore.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideBtnMore() {
        btnShowMore.setVisibility(View.GONE);
    }

    @Override
    public void setMaxLinesDescription(int maxLines) {
        tvDescription.setMaxLines(maxLines);
    }

    @Override
    public void setTextBtn(int label) {
        btnShowMore.setText(label);
    }

    @Override
    public void setLink(String url) {
        tvLink.setText(
                Html.fromHtml(
                        "<a href=\"" + url + "\">" + tvLink.getText().toString() + "</a> "));
        tvLink.setMovementMethod(LinkMovementMethod.getInstance());
    }
}
