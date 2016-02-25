package com.ekart.hackfest.foodmania.services;

import com.ekart.hackfest.foodmania.model.CustomerOrderEntity;
import com.ekart.hackfest.foodmania.repository.*;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * Created by vishal.bhandari on 25/02/16.
 */
public class MerchantService {

    private static Logger logger = Logger.getLogger(MerchantService.class);
    private MerchantDao merchantDao;


    public MerchantService(MerchantDao merchantDao)
    {
        this.merchantDao = merchantDao;
    }
    public List<CustomerOrderEntity> getOrderListByMerchant(String merchantId)
    {
        return merchantDao.getOrderListByMerchant(merchantId).getCustomerOrderEntities();
    }



}
