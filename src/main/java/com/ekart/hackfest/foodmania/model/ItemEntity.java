package com.ekart.hackfest.foodmania.model;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by rishabh.sood on 23/02/16.
 */
@Data
@Entity
@Table(name = "Item", schema = "", catalog = "foodmania")
public class ItemEntity {

    @Id
    @Column(name = "ITEMID")
    private String itemid;

    @Basic
    @Column(name = "ITEMDESC")
    private String itemdesc;

    @Basic
    @Column(name = "AVAILABLE")
    private byte available;

    @Basic
    @Column(name = "PRICE")
    private int price;

    @ManyToOne
    @JoinColumn(name = "MENUID", referencedColumnName = "MENUID", nullable = false)
    private MenuEntity menuEntity;

}
