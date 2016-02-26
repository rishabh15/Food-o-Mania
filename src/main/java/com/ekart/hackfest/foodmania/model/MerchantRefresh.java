package com.ekart.hackfest.foodmania.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;

/**
 * Created by rishabh.sood on 26/02/16.
 */
@Data
public class MerchantRefresh {
    public String orderId;
    public String status;
    public int price;
    public String name;
    public String phone;
}
