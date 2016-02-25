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
@Table(name = "Menu", schema = "", catalog = "foodmania")
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id")
public class MenuEntity {

    @Id
    @Column(name = "MENUID")
    private String menuid;

    @Basic
    @Column(name = "ACTIVE")
    private String active;

    @ManyToOne
    @JoinColumn(name = "MERCHANTID", referencedColumnName = "MERCHANTID", nullable = false)
    private MerchantInfoEntity merchantInfoEntity;

    @OneToMany(mappedBy = "menuEntity",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    private List<ItemEntity> itemEntities;

}
