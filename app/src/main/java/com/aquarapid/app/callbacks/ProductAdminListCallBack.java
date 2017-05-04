package com.aquarapid.app.callbacks;


import com.aquarapid.app.dao.types.Product;

public interface ProductAdminListCallBack {
    void editProduct(Product product);

    void delProduct(Product product);
}
