package com.ekart.hackfest.foodmania.services;

import com.ekart.hackfest.foodmania.model.CustomerOrderEntity;
import com.ekart.hackfest.foodmania.model.ItemForOrderEntity;
import com.ekart.hackfest.foodmania.model.Status;
import com.ekart.hackfest.foodmania.repository.*;
import org.apache.log4j.Logger;
import org.hibernate.Query;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vishal.bhandari on 25/02/16.
 */
public class CustomerService {

    private static Logger logger = Logger.getLogger(CustomerService.class);
    private CustomerDao customerDao;
    private CustomerOrderDao customerOrderDao;
    private ItemDao itemDao;
    private MenuDao menuDao;
    private ItemForOrderDao itemForOrderDao;

    public CustomerService(CustomerOrderDao customerOrderDao)
    {
        this.customerOrderDao = customerOrderDao;

    }


    public CustomerOrderEntity updateOrder(String orderId,Status status)
    {
        return customerOrderDao.updateOrder(orderId,status);
    }


    public CustomerOrderEntity getItemList(String orderId) {
        return customerOrderDao.getItemList(orderId);
    }

    public CustomerOrderEntity createOrder(CustomerOrderEntity customerOrderEntity) {

        String orderId = setOrderId();
        customerOrderEntity.setOrderid(orderId);
        return customerOrderDao.createOrder(customerOrderEntity);
    }

}
