package com.aquarapid.app.adapters;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.aquarapid.app.R;

public class CartViewHolder<T> extends RecyclerView.ViewHolder  {
    public final View mView;
    public final ImageView image;
    public final TextView title;
    public final TextView desc;
    public final TextView qty ;
    public final ImageButton cart ;


    public T mItem;

    public CartViewHolder(View view) {
        super(view);
        mView = view;
        image = (ImageView) view.findViewById(R.id.imageItem);
        desc = (TextView) view.findViewById(R.id.desc);
        title = (TextView) view.findViewById(R.id.title);
        qty = (TextView) view.findViewById(R.id.qty );
        cart = (ImageButton) view.findViewById(R.id.btnCart );
    }

    @Override
    public String toString() {
        return super.toString() + " '" + title.getText() + "'";
    }
}