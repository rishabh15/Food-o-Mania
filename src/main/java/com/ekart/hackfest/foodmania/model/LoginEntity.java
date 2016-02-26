package com.ekart.hackfest.foodmania.model;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by vishal.bhandari on 26/02/16.
 */
@Data
@Entity
@Table(name = "Login", schema = "", catalog = "foodmania")
public class LoginEntity {

    @Id
    @Column(name = "USERNAME")
    private String username;

    @Basic
    @Column(name = "PASSWORD")
    private String password;

    @Basic
    @Column(name = "TYPE")
    private String type;

}
