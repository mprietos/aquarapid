package com.aquarapid.app.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.RecyclerView;

import com.aquarapid.app.R;
import com.aquarapid.app.callbacks.ProductListCallBack;
import com.aquarapid.app.dao.types.Product;
import com.bumptech.glide.Glide;

import java.util.List;


public class ItemsRecyclerViewAdapter extends RecyclerView.Adapter<CommonViewHolder<Product>> {
    private final ProductListCallBack mListener;
    private final boolean mIsCart;
    private List<Product> mValues;

    private final Context mContext;

    public ItemsRecyclerViewAdapter(Context context, List<Product> items, ProductListCallBack listener, boolean isCart) {
        mValues = items;
        mContext = context;
        mListener = listener;
        mIsCart = isCart;
    }

    @Override
    public CommonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_shop, parent, false);
        return new CommonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final CommonViewHolder holder, final int position) {
        holder.mItem = mValues.get(position);
        if (this.mIsCart)
            holder.cart.setVisibility(View.GONE);
        else
            holder.cart.setVisibility(View.VISIBLE);


        holder.title.setText(mValues.get(position).getCode());
        holder.desc.setText(mValues.get(position).getDesc());


        String urlImage = mValues.get(position).getFoto();
       // Glide.with(mContext).load(urlImage).into(holder.image);

        holder.cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.addChart(mValues.get(position));
            }
        });

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onClickProduct(mValues.get(position));
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
