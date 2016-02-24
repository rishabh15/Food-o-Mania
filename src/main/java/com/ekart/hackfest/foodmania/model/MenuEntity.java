package com.ekart.hackfest.foodmania.model;

import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by rishabh.sood on 23/02/16.
 */
@Data
@Entity
@Table(name = "Menu", schema = "", catalog = "foodmania")
public class MenuEntity {

    @Id
    @Column(name = "MENUID")
    private String menuid;

    @Basic
    @Column(name = "STARTTIME")
    private Timestamp starttime;

    @Basic
    @Column(name = "ENDTIME")
    private Timestamp endtime;

    @ManyToOne
    @JoinColumn(name = "MERCHANTID", referencedColumnName = "MERCHANTID", nullable = false)
    private MerchantInfoEntity merchantInfoEntity;

    @OneToMany(mappedBy = "MenuEntity",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    private List<ItemEntity> itemEntities;

}
