package com.grability.msalcedo.itunesstore_test.view.helper.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.grability.msalcedo.itunesstore_test.R;
import com.grability.msalcedo.itunesstore_test.model.Genre;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * Created by Mariangela Salcedo (msalcedo047@gmail.com) on 08/10/16.
 * Copyright (c) 2016 m-salcedo. All rights reserved.
 */
public class CategoryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    // ---------------------------------------------------------------------------------------------
    // ---------------------------------------------------------------------------------------------
    // Constants
    // ---------------------------------------------------------------------------------------------
    // ---------------------------------------------------------------------------------------------

    private static final String TAG = CategoryAdapter.class.getSimpleName();

    // ---------------------------------------------------------------------------------------------
    // ---------------------------------------------------------------------------------------------
    // Fields
    // ---------------------------------------------------------------------------------------------
    // ---------------------------------------------------------------------------------------------

    private OnItemClickListener mListener;
    private List<Genre> mItems;

    // ---------------------------------------------------------------------------------------------
    // ---------------------------------------------------------------------------------------------
    // Methods
    // ---------------------------------------------------------------------------------------------
    // ---------------------------------------------------------------------------------------------

    public static class ViewHolderItem extends RecyclerView.ViewHolder {
        @Bind(R.id.tvTitle)
        public TextView tvTitle;

        public ViewHolderItem(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }
    }

    public CategoryAdapter(List<Genre> list, Context context,
                           OnItemClickListener listener) {
        mItems = list;
        mListener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater vi = LayoutInflater.from(parent.getContext());
        View v = vi.inflate(R.layout.view_item_category, parent, false);
        return new ViewHolderItem(v);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        final ViewHolderItem viewHolderItem = (ViewHolderItem) holder;

        final Genre item = mItems.get(position);

        viewHolderItem.tvTitle.setText(item.getTitle());

        viewHolderItem.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (holder.getAdapterPosition() >= 0) {
                    mListener.onClickItem(view, item);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public interface OnItemClickListener {
        void onClickItem(View view, Genre category);
    }

}