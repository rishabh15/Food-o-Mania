package com.ekart.hackfest.foodmania.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by rishabh.sood on 23/02/16.
 */
@Data
@Entity
@Table(name = "CustomerOrder", schema = "", catalog = "foodmania")
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id")
public class CustomerOrderEntity {

    @Id
    @Column(name = "ORDERID")
    private String orderid;

    @Basic
    @Column(name = "PRICE")
    private int price;

    @Basic
    @Column(name = "TIME")
    private Timestamp time;

    @Basic
    @Column(name = "COMMENTS")
    private String comments;

    @Basic
    @Column(name = "STATUS")
    private String status;

    @ManyToOne
    @JoinColumn(name = "CUSTOMERID", referencedColumnName = "CUSTOMERID", nullable = false)
    private CustomerEntity customerEntity;
}
