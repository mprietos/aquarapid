package com.aquarapid.app.dao.types;


import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;

import java.sql.Timestamp;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Entity mapped to table "ProductsByOrder".
 */
@Entity
public class ProductsByOrder {
    @NotNull
    private Long idProduct;
    private int units;
    private double price;
    @Generated(hash = 430789269)
    public ProductsByOrder(@NotNull Long idProduct, int units, double price) {
        this.idProduct = idProduct;
        this.units = units;
        this.price = price;
    }
    @Generated(hash = 1726831838)
    public ProductsByOrder() {
    }
    public Long getIdProduct() {
        return this.idProduct;
    }
    public void setIdProduct(Long idProduct) {
        this.idProduct = idProduct;
    }
    public int getUnits() {
        return this.units;
    }
    public void setUnits(int units) {
        this.units = units;
    }
    public double getPrice() {
        return this.price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
}
