package com.aquarapid.app.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aquarapid.app.R;
import com.aquarapid.app.callbacks.ProductListCallBack;
import com.aquarapid.app.dao.types.ItemsCart;
import com.aquarapid.app.dao.types.Product;
import com.bumptech.glide.Glide;

import java.util.List;


public class ItemsCartRecyclerViewAdapter extends RecyclerView.Adapter<CartViewHolder<Product>> {
    private final ProductListCallBack mListener;
    private final boolean mIsCart;
    private List<ItemsCart> mValues;

    private final Context mContext;

    public ItemsCartRecyclerViewAdapter(Context context, List<ItemsCart> items, ProductListCallBack listener, boolean isCart) {
        mValues = items;
        mContext = context;
        mListener = listener;
        mIsCart = isCart;
    }

    @Override
    public CartViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_cart, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final CartViewHolder holder, final int position) {
        holder.mItem = mValues.get(position);


        holder.title.setText("Codigo " + mValues.get(position).getCode());
        holder.desc.setText(mValues.get(position).getDesc());
        holder.qty.setText(mValues.get(position).getQty() + " uds");


        String urlImage = mValues.get(position).getFoto();
        Glide.with(mContext).load(urlImage).into(holder.image);


    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public void fillData(List<ItemsCart> characters) {
        mValues = (characters);
    }
}
