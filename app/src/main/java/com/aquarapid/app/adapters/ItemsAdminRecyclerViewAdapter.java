package com.aquarapid.app.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aquarapid.app.R;
import com.aquarapid.app.callbacks.ProductAdminListCallBack;
import com.aquarapid.app.dao.types.Product;
import com.bumptech.glide.Glide;

import java.util.List;


public class ItemsAdminRecyclerViewAdapter extends RecyclerView.Adapter<AdminViewHolder<Product>> {
    private final ProductAdminListCallBack mListener;
    private List<Product> mValues;

    private final Context mContext;

    public ItemsAdminRecyclerViewAdapter(Context context, List<Product> items, ProductAdminListCallBack listener) {
        mValues = items;
        mContext = context;
        mListener = listener;
    }

    @Override
    public AdminViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_admin, parent, false);
        return new AdminViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final AdminViewHolder holder, final int position) {
        holder.mItem = mValues.get(position);


        holder.title.setText(mValues.get(position).getCode());
        holder.desc.setText(mValues.get(position).getDesc());


        String urlImage = mValues.get(position).getFoto();
      //  Glide.with(mContext).load(urlImage).into(holder.image);

        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.editProduct(mValues.get(position));
            }
        });

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.delProduct(mValues.get(position));
            }
        });

    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public void fillData(List<Product> characters) {
        mValues = (characters);
    }
}
