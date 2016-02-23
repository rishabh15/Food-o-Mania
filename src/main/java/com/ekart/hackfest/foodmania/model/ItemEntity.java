package com.ekart.hackfest.foodmania.model;

import javax.persistence.*;

/**
 * Created by rishabh.sood on 23/02/16.
 */
@Entity
@Table(name = "Item", schema = "", catalog = "foodmania")
public class ItemEntity {
    private String itemid;
    private String itemdesc;
    private byte available;
    private int price;

    @Id
    @Column(name = "ITEMID")
    public String getItemid() {
        return itemid;
    }

    public void setItemid(String itemid) {
        this.itemid = itemid;
    }

    @Basic
    @Column(name = "ITEMDESC")
    public String getItemdesc() {
        return itemdesc;
    }

    public void setItemdesc(String itemdesc) {
        this.itemdesc = itemdesc;
    }

    @Basic
    @Column(name = "AVAILABLE")
    public byte getAvailable() {
        return available;
    }

    public void setAvailable(byte available) {
        this.available = available;
    }

    @Basic
    @Column(name = "PRICE")
    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ItemEntity that = (ItemEntity) o;

        if (available != that.available) return false;
        if (price != that.price) return false;
        if (itemid != null ? !itemid.equals(that.itemid) : that.itemid != null) return false;
        if (itemdesc != null ? !itemdesc.equals(that.itemdesc) : that.itemdesc != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = itemid != null ? itemid.hashCode() : 0;
        result = 31 * result + (itemdesc != null ? itemdesc.hashCode() : 0);
        result = 31 * result + (int) available;
        result = 31 * result + price;
        return result;
    }
}
