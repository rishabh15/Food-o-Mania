package com.ekart.hackfest.foodmania.model;

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
@Table(name = "MerchantInfo", schema = "", catalog = "foodmania")
public class MerchantInfoEntity {

    @Id
    @Column(name = "MERCHANTID")
    private String merchantid;

    @Basic
    @Column(name = "NAME")
    private String name;

    @Basic
    @Column(name = "EMAIL")
    private String email;

    @Basic
    @Column(name = "PHONE")
    private String phone;

    @OneToMany(mappedBy = "merchantInfoEntity",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    private List<MenuEntity> menuEntities;

    @OneToMany(mappedBy = "merchantInfoEntity",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    private List<CustomerOrderEntity> customerOrderEntities;

}
