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
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id")
public class ReferenceCountEntity {

    @Id
    @Column(name = "Name")
    private String name;

    @Basic
    @Column(name = "COUNT")
    private long count;
}
