package com.aquarapid.app.dao.types;


import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;

import org.greenrobot.greendao.annotation.Generated;

/**
 * Entity mapped to table "ItemsCart".
 */
@Entity
public class ItemsCart {
    @Id(autoincrement = true)
    private Long id;

    @NotNull
    private String cif;
    private String code;
    private String desc;
    private String foto;
    private double price;
    private int qty;
    public ItemsCart(String cif, String code, String desc, String foto, double price, int qty) {
        this.cif = cif;
        this.code = code;
        this.desc = desc;
        this.foto = foto;
        this.price = price;
        this.qty = qty;
    }

    @Generated(hash = 2126554988)
    public ItemsCart(Long id, @NotNull String cif, String code, String desc, String foto, double price,
            int qty) {
        this.id = id;
        this.cif = cif;
        this.code = code;
        this.desc = desc;
        this.foto = foto;
        this.price = price;
        this.qty = qty;
    }

    @Generated(hash = 1319408305)
    public ItemsCart() {
    }

    public String getCif() {
        return cif;
    }

    public void setCif(String cif) {
        this.cif = cif;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getQty() {
        return this.qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }
}
