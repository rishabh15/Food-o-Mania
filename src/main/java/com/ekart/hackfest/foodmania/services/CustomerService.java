package com.ekart.hackfest.foodmania.services;

import com.ekart.hackfest.foodmania.model.CustomerOrderEntity;
import com.ekart.hackfest.foodmania.repository.CustomerDao;
import com.ekart.hackfest.foodmania.repository.CustomerOrderDao;
import com.ekart.hackfest.foodmania.repository.ItemDao;
import com.ekart.hackfest.foodmania.repository.MenuDao;
import org.apache.log4j.Logger;

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

    public CustomerService(CustomerOrderDao customerOrderDao)
    {
        this.customerOrderDao = customerOrderDao;
    }
    public List<CustomerOrderEntity> getOrderListByMerchant(String merchantId)
    {
        return customerOrderDao.getOrderListByMerchant(merchantId);
    }



}
