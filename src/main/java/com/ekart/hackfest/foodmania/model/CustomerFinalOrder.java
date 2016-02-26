package com.ekart.hackfest.foodmania.model;

import lombok.Data;

import java.util.List;

/**
 * Created by vishal.bhandari on 26/02/16.
 */
@Data
public class CustomerFinalOrder {
    private String orderId;
    private List<String> itemNameList;
    private String time;
    private String status;

}
