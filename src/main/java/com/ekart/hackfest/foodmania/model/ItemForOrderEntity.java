package com.ekart.hackfest.foodmania.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;

import javax.persistence.*;

/**
 * Created by rishabh.sood on 23/02/16.
 */
@Data
@Entity
@Table(name = "ItemForOrder", schema = "", catalog = "foodmania")
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id")
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

    @Basic
    @Column(name = "COUNT")
    private int count;

    @ManyToOne
    @JoinColumn(name = "ORDERID", referencedColumnName = "ORDERID", nullable = false)
    private CustomerOrderEntity customerOrderEntity;

}
