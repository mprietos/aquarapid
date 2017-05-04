package com.aquarapid.app.dao.types;


import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Entity mapped to table "Product".
 */
@Entity
public class Product {

    @Id(autoincrement = true)
    private Long id;

    @NotNull
    private String code;
    private String desc;
    private String foto;
    private double price;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Product(String code, String desc, String foto, double price) {

        this.code = code;
        this.desc = desc;
        this.foto = foto;
        this.price = price;
    }

    @Generated(hash = 58919731)
    public Product(Long id, @NotNull String code, String desc, String foto,
            double price) {
        this.id = id;
        this.code = code;
        this.desc = desc;
        this.foto = foto;
        this.price = price;
    }

    @Generated(hash = 1890278724)
    public Product() {
    }
}
