package com.ekart.hackfest.foodmania.services;

import com.ekart.hackfest.foodmania.model.CustomerOrderEntity;
import com.ekart.hackfest.foodmania.model.ItemForOrderEntity;
import com.ekart.hackfest.foodmania.model.ReferenceCountEntity;
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
    private ReferenceCountDao referenceCountDao;
    private ItemDao itemDao;
    private MenuDao menuDao;
    private ItemForOrderDao itemForOrderDao;

    public CustomerService(CustomerOrderDao customerOrderDao,ReferenceCountDao referenceCountDao)
    {
        this.customerOrderDao = customerOrderDao;
        this.referenceCountDao = referenceCountDao;
    }


    public CustomerOrderEntity updateOrder(String orderId,Status status)
    {
        return customerOrderDao.updateOrder(orderId,status);
    }


    public List<CustomerOrderEntity> getOrder()
    {
        List<CustomerOrderEntity> customerOrderEntities=customerOrderDao.getOrder();
        for(CustomerOrderEntity customerOrderEntity:customerOrderEntities)
        {
           // customerOrderEntity.setCustomerEntity(null);
        }
        return customerOrderEntities;
    }




    public CustomerOrderEntity getItemList(String orderId) {
        return customerOrderDao.getItemList(orderId);
    }

    public List<CustomerOrderEntity> createOrder(List<CustomerOrderEntity> customerOrderEntityList) {

        for(CustomerOrderEntity customerOrderEntity:customerOrderEntityList) {
            ReferenceCountEntity referenceCount = referenceCountDao.getReferenceCount();
            long count = referenceCount.getCount();
            String orderId = "ORD" + count;
            count++;
            referenceCount.setCount(count);
            referenceCountDao.addReferenceCount(referenceCount);
            customerOrderEntity.setOrderid(orderId);
        }
        return customerOrderDao.createOrder(customerOrderEntityList);
    }


}
