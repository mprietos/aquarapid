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


public class ItemsCartRecyclerViewAdapter extends RecyclerView.Adapter<CommonViewHolder<Product>> {
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
    public CommonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_shop, parent, false);
        return new CommonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final CommonViewHolder holder, final int position) {
        holder.mItem = mValues.get(position);
            holder.cart.setVisibility(View.GONE);


        holder.title.setText(mValues.get(position).getCode());
        holder.desc.setText(mValues.get(position).getDesc());


        String urlImage = mValues.get(position).getFoto();
       // Glide.with(mContext).load(urlImage).into(holder.image);


    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public void fillData(List<ItemsCart> characters) {
        mValues = (characters);
    }
}
