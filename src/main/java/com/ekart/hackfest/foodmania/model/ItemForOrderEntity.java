package com.ekart.hackfest.foodmania.model;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by rishabh.sood on 23/02/16.
 */
@Data
@Entity
@Table(name = "ItemForOrder", schema = "", catalog = "foodmania")
public class ItemForOrderEntity {

    @Id
    @Column(name = "ITEMID")
    private String itemid;


    @Basic
    @Column(name = "ITEMDESC")
    private String itemdesc;

    @Basic
    @Column(name = "PRICE")
    private int price;

    @ManyToOne
    @JoinColumn(name = "ORDERID", referencedColumnName = "ORDERID", nullable = false)
    private CustomerOrderEntity customerOrderEntity;

}
