package com.grability.msalcedo.itunesstore_test.view.helper.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.grability.msalcedo.itunesstore_test.R;
import com.grability.msalcedo.itunesstore_test.model.Entry;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Mariangela Salcedo (msalcedo047@gmail.com) on 08/10/16.
 * Copyright (c) 2016 m-salcedo. All rights reserved.
 */
public class ItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    // ---------------------------------------------------------------------------------------------
    // ---------------------------------------------------------------------------------------------
    // Constants
    // ---------------------------------------------------------------------------------------------
    // ---------------------------------------------------------------------------------------------

    private static final String TAG = ItemAdapter.class.getSimpleName();

    // ---------------------------------------------------------------------------------------------
    // ---------------------------------------------------------------------------------------------
    // Fields
    // ---------------------------------------------------------------------------------------------
    // ---------------------------------------------------------------------------------------------

    private List<Entry> mItems;
    private OnItemClickListener mListener;
    private Context mContext;
    private int mCategory;

    // ---------------------------------------------------------------------------------------------
    // ---------------------------------------------------------------------------------------------
    // Methods
    // ---------------------------------------------------------------------------------------------
    // ---------------------------------------------------------------------------------------------

    public void addItems(List<Entry> items, int category) {
        mItems.addAll(items);
        mCategory = category;
        notifyDataSetChanged();
    }

    public void clear() {
        mItems.clear();
        notifyDataSetChanged();
    }

    public static class ViewHolderBody extends RecyclerView.ViewHolder {
        @Bind(R.id.ivIcon)
        public ImageView ivIcon;
        @Bind(R.id.tvTitle)
        public TextView tvTitle;
        @Bind(R.id.tvRight)
        public TextView tvRight;
        @Bind(R.id.tvCategory)
        public TextView tvCategory;

        public ViewHolderBody(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }
    }

    public ItemAdapter(List<Entry> list, OnItemClickListener listener, Context context) {
        mItems = list;
        mListener = listener;
        mContext = context;
        mCategory = 0;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolderBody(LayoutInflater.from(parent.getContext()).inflate(R.layout.view_item, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        final ViewHolderBody viewHolderBody = (ViewHolderBody) holder;
        final Entry item = mItems.get(position);

        Glide.with(mContext)
                .load(item.getImage().getLabel())
                .into(viewHolderBody.ivIcon);

        viewHolderBody.tvTitle.setText(item.getImName().getLabel());

        if (mCategory == 0) {
            viewHolderBody.tvCategory.setVisibility(View.VISIBLE);
            viewHolderBody.tvCategory.setText(item.getCategory().getAttributes().getLabel());
        } else {
            viewHolderBody.tvCategory.setVisibility(View.GONE);
        }

        viewHolderBody.tvRight.setText(item.getImArtist().getLabel());

        viewHolderBody.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onClick(view, item);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }


    // Interface for receiving click events from list items
    public interface OnItemClickListener {
        void onClick(View view, Entry item);
    }

}
