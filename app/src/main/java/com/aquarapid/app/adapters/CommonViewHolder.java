package com.aquarapid.app.adapters;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.aquarapid.app.R;

public class CommonViewHolder<T> extends RecyclerView.ViewHolder  {
    public final View mView;
    public final TextView title;
    public final TextView desc;
    public final ImageView image ;
    public final ImageButton cart ;


    public T mItem;

    public CommonViewHolder(View view) {
        super(view);
        mView = view;
        desc = (TextView) view.findViewById(R.id.desc);
        title = (TextView) view.findViewById(R.id.title);
        image = (ImageView) view.findViewById(R.id.imageItem );
        cart = (ImageButton) view.findViewById(R.id.btnCart );
    }

    @Override
    public String toString() {
        return super.toString() + " '" + title.getText() + "'";
    }
}