package com.ekart.hackfest.foodmania.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

/**
 * Created by rishabh.sood on 23/02/16.
 */
@Data
@Entity
@Table(name = "Customer", schema = "", catalog = "foodmania")
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id")
public class CustomerEntity {

    @Id
    @Column(name = "CUSTOMERID")
    private String customerid;

    @Basic
    @Column(name = "NAME")
    private String name;

    @Basic
    @Column(name = "PHONE")
    private String phone;

    @Basic
    @Column(name = "EMAIL")
    private String email;

    @OneToMany(mappedBy = "customerEntity",cascade = CascadeType.ALL)
    @Fetch(FetchMode.SUBSELECT)
    private List<CustomerOrderEntity> customerOrderEntities;
}
