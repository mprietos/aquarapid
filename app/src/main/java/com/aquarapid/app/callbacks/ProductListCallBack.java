package com.aquarapid.app.callbacks;

import com.aquarapid.app.dao.types.Product;

public interface ProductListCallBack {
     void onClickProduct(Product product);

     void addChart(Product product);
}
