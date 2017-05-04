package com.aquarapid.app.dao.types;



import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;

import java.sql.Timestamp;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Entity mapped to table "Order".
 */
@Entity
public class Orders {
    @Id(autoincrement = true)
    private Long id;

    @NotNull
    private String cif;
    private Long date;
    private double transport;
    @Generated(hash = 1986804647)
    public Orders(Long id, @NotNull String cif, Long date, double transport) {
        this.id = id;
        this.cif = cif;
        this.date = date;
        this.transport = transport;
    }
    @Generated(hash = 1753857294)
    public Orders() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getCif() {
        return this.cif;
    }
    public void setCif(String cif) {
        this.cif = cif;
    }
    public Long getDate() {
        return this.date;
    }
    public void setDate(Long date) {
        this.date = date;
    }
    public double getTransport() {
        return this.transport;
    }
    public void setTransport(double transport) {
        this.transport = transport;
    }

}
