package com.ekart.hackfest.foodmania.services;

import com.ekart.hackfest.foodmania.model.*;
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

    public List<CustomerFinalOrder> createFinalOrder(List<CustomerOrderEntity> customerOrderEntityList1)
    {
        List<CustomerFinalOrder> customerFinalOrderList = new ArrayList<CustomerFinalOrder>();
        for (CustomerOrderEntity customerOrderEntity : customerOrderEntityList1) {
            CustomerFinalOrder customerFinalOrder = new CustomerFinalOrder();
            customerFinalOrder.setOrderId(customerOrderEntity.getOrderid());
            customerFinalOrder.setTime(customerOrderEntity.getTime());

            List<String> itemNameList = new ArrayList<String>();
            for (ItemForOrderEntity itemForOrderEntity : customerOrderEntity.getItemForOrderEntities()) {
                String itemName = itemForOrderEntity.getItemdesc();
                itemNameList.add(itemName);
            }
            customerFinalOrder.setItemNameList(itemNameList);
            customerFinalOrderList.add(customerFinalOrder);

        }
        return customerFinalOrderList;
    }

}
