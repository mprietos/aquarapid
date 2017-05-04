package com.aquarapid.app.dao.types;


import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Entity mapped to table "Customer".
 */
@Entity
public class Customer {

    @Id(autoincrement = true)
    private Long id;


    @Index(unique = true)
    private String cif;
    private String name;
    private String email;
    private String pwd;

    public Customer(String cif, String name, String email, String pwd) {
        this.cif = cif;
        this.name = name;
        this.email = email;
        this.pwd = pwd;
    }

    @Generated(hash = 229043829)
    public Customer(Long id, String cif, String name, String email, String pwd) {
        this.id = id;
        this.cif = cif;
        this.name = name;
        this.email = email;
        this.pwd = pwd;
    }

    @Generated(hash = 60841032)
    public Customer() {
    }

    public String getCif() {
        return cif;
    }

    public void setCif(String cif) {
        this.cif = cif;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
