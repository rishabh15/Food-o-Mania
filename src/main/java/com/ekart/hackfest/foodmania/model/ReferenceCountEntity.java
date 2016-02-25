package com.ekart.hackfest.foodmania.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;

import javax.persistence.*;

/**
 * Created by rishabh.sood on 25/02/16.
 */
@Data
@Entity
@Table(name = "ReferenceCount", schema = "", catalog = "foodmania")
public class ReferenceCountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Basic
    @Column(name = "COUNT")
    private long count;
}
